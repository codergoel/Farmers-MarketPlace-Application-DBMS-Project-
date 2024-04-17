package util;

public class UserSession {
    private static int userId; // Holds the currently logged-in user's ID
    private static boolean isLoggedIn = false;

    public static void login(int userId) {
        UserSession.userId = userId;
        isLoggedIn = true;
    }

    public static void logout() {
        userId = -1; // Reset user ID
        isLoggedIn = false;
    }

    public static int getUserId() {
        return userId;
    }

    public static boolean isLoggedIn() {
        return isLoggedIn;
    }
}
