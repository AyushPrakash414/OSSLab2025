@RestController
@RequestMapping("/api/users")
public class UserController {

    // 1. PLAINTEXT PASSWORD USAGE
    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request) {

        String email = request.getEmail();
        String password = request.getPassword();

        // Password is being used/stored directly without hashing
        User user = userRepository.findByEmailAndPassword(email, password);

        return user != null ? "Login successful" : "Invalid credentials";
    }

    // 2. MISSING AUTHORIZATION
    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {

        // No authentication/authorization check
        // Any user can request another user's data
        return userRepository.findById(id).orElse(null);
    }

    // 3. OS COMMAND INJECTION / UNSAFE COMMAND EXECUTION
    @PostMapping("/execute")
    public String executeCommand(@RequestParam String command) throws Exception {

        // User-controlled input is passed directly to the OS
        Runtime.getRuntime().exec(command);

        return "Command executed";
    }
}