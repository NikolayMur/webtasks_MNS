package constants;

public interface Constants {
    //public static final String CONNECTING_URL = "jdbc:mysql://localhost:3306/mydbtest";
    public static final String CONNECTING_URL = "jdbc:mysql://localhost:3306/student_progress";
    public static final String CONNECTING_USER = "root";
    public static final String CONNECTING_PASSWORD = "root";

    public static final int CONNECTING_POOL_SIZE = 5;

    public static final String CONTEXT = "CONTEXT";
    public static final String VALIDATION_MESSAGE = "VALIDATION_MESSAGE";
    public static final String CURRENT_SESSION_ACCOUNT = "CURRENT_SESSION_ACCOUNT";
    public static final String CURRENT_ROLE = "CURRENT_ROLE";
    public static final String CURRENT_MAPPING = "CURRENT_MAPPING";

    public static final int ROLE_ADMIN = 1;
    public static final int ROLE_STUDENT = 2;
}
