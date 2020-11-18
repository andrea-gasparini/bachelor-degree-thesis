@Getter
@Builder
public class User
{
	@Setter
	private Integer id;
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private String email;
	private String organization;
	private String country;
	private int type;
	private boolean active;
}