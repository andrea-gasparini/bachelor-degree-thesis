@PostMapping(path = "/signup")
public ResponseEntity signUp(@RequestParam String username, @RequestParam String email, @RequestParam String firstName, @RequestParam String lastName, @RequestParam String organization, @RequestParam String country)
{
	if (StringUtils.areEmpty(username, email, firstName, lastName, organization, country))
		return RestMessage.badRequest()
				.body("All fields are mandatory.")
				.toResponseEntity();

	UserDAO userDAO = new UserSQL();
	AccessTokenDAO tokenDAO = new AccessTokenSQL();

	Optional<RestMessage> constraintViolations = getConstraintsViolations(userDAO, username, email);
	if (constraintViolations.isPresent())
		return constraintViolations.get()
				.accessToken(accessToken)
				.toResponseEntity();

	User user = User.builder()
			.username(username)
			.email(emailService.checkEmailFormat(email))
			.firstName(firstName)
			.lastName(lastName)
			.password(StringUtils.generatePassword())
			.organization(organization)
			.country(country)
			.type(UserType.DEFAULT.getId())
			.active(true)
			.build();

	emailService.sendNewUserEmail(user);

	userDAO.insertUser(user);

	Timestamp newExp = TokenUtils.getNewTimestamp(millisRefresh);
	AccessToken newToken = AccessToken.random(newExp, user.getId());

	tokenDAO.insertAccessToken(newToken);

	return RestMessage.ok()
			.body(user)
			.accessToken(newToken)
			.toResponseEntity();
}