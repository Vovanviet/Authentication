package com.authentication;

public class Menu {
    public static void mainMenu(){
        System.out.println("\nChoice:\n1.Login\n2.Sign Up\nYour Choice:");
    }
    public static void loginFail(){
        System.out.println("\nPassword is not correct!!!Your must be perform:\n1.Login\n2.Forgot Password\nYour Choice:");
    }
    public static void loginSuccess(){
        System.out.println("\nLogin Success \n **************Menu*****************\n1.Change Username\n2.Change Email\n3.Change Password\n4.Logout\n5.Exit Application\nYour Choice:");
    }
}
