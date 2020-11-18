@Component
public class AdminFilter extends OncePerRequestFilter
{
	private final Pattern pattern = Pattern.compile("/api/admin/.*");

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
	{
		AccessToken accessToken = TokenUtils.getAttribute(request);

		User user = new UserSQL().getUser(accessToken.getUser()).get();
		int userType = user.getType();
		int adminUserType = UserType.ADMIN.getId();

		if (userType == adminUserType) chain.doFilter(request, response);
		else response.setStatus(HttpServletResponse.SC_FORBIDDEN);
	}

	@Override
	protected boolean shouldNotFilter(HttpServletRequest request)
	{
		return !pattern.matcher(request.getServletPath()).matches();
	}
}