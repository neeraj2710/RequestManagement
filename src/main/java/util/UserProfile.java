package util;

public class UserProfile {
    private static String username;
    private static String department;

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        UserProfile.username = username;
    }

    public static String getDepartment() {
        return department;
    }

    public static void setDepartment(String department) {
        UserProfile.department = department;
    }
}
