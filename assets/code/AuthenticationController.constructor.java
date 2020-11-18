@RestController
public class AuthenticationController
{
	// [...]

	private final EmailService emailService;

	@Autowired
	public AuthenticationController(EmailService emailService)
	{
		this.emailService = emailService;
	}

	// [...]
}