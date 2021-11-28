package main;

public final class UserSession {
    private static UserSession instance;
    
    private User currentUser;
    
    private UserSession(User currUser) {
        this.currentUser = currUser;
    }
    
    public static UserSession getInstance(User currUser) {
        if (instance == null) {
            instance = new UserSession(currUser);
        }
        return instance;
    }
    
    public void cleanUserSession() {
        currentUser = null;
    }
    
    @Override
    public String toString() {
        return "UserSession [" + currentUser + "]";
    }
    
}
