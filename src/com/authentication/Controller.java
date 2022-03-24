package com.authentication;

import java.util.List;
import java.util.Scanner;

public class Controller {
    String username;
    String password;
    Repository repository=new Repository();
    List<User>users=repository.getDataGSON();
    Scanner scanner=new Scanner(System.in);
    public void home(){
        Menu.mainMenu();
        int choose=scanner.nextInt();
        switch (choose){
            case 1:
                login();
                break;
            case 2:
                createNewAccount();
                break;
            default:
                System.out.println("You are choose is not invalid!");
                Menu.mainMenu();
                break;
        }
    }
    public void login(){
        boolean checklogin=false;
        while (!checklogin){
            System.out.println("Enter username:");
            username=scanner.nextLine();
            int count=0;
            for (int i=0;i<users.size();i++) {
                if (username.equals(users.get(i).getUsername())) {
                    count++;
                    System.out.println("Enter Password:");
                    password = scanner.nextLine();
                    if (password.equals(users.get(i).getPassword())) {
                        loginSuccess();
                        checklogin = true;
                    } else {
                        loginFail();
                        break;
                    }
                }
            }if (count==0){
                System.out.println("Check again username!");
            }
        }

    }
    public void loginSuccess(){
        System.out.println("Welcome"+username+",You can the performance:");
        Menu.loginSuccess();
        int choose=scanner.nextInt();
        scanner.nextLine();
        switch (choose){
            case 1:
                changeUsername();
                break;
            case 2:
                changeEmail();
                break;
            case 3:
                changePassword();
                break;
            case 4:
                home();
                break;
            case 5:
                System.out.println("See you again!");
                System.exit(1);
                break;
            default:break;
        }
    }
    public void changePassword(){
        System.out.println("Enter old password:");
        password=scanner.nextLine();
        int count=0;
        for (int i=0;i<users.size();i++){
            if (password.equals(users.get(i).getPassword())) {
                count++;
                boolean check=false;
                String newPassword=null;
                while (!check){
                    try {
                        System.out.println("Enter new password:");
                        newPassword=Validate.validatePassword(scanner.nextLine());
                        check=true;
                    }catch (RuntimeException e){
                        System.out.println(e);
                    }
                    }
                users.get(i).setPassword(newPassword);
                System.out.println("Change Password Success,Please Login Again...");
                login();
                password=newPassword;

                }

            }if (count==0){
            System.out.println("Password is not correct");
        }
    }
    public void changeUsername(){
        boolean check=false;

        while (!check){
            try {
                System.out.println("Enter new username:");
                String newUsername=scanner.nextLine();
                for (int i=0;i<users.size();i++){
                    if (username.equals(users.get(i).getUsername())) {
                        if (!username.equals(users.get(i).getUsername())){
                            users.get(i).setUsername(newUsername);
                            username=newUsername;
                            System.out.println("Change Username Success");
                            check=true;
                        }else {
                            throw new RuntimeException("Username Already exist");
                        }
                     }
                }
            } catch (RuntimeException e) {
                System.out.println(e.getMessage()+",Please Enter Username");
            }
        }
        loginSuccess();
    }
    public void changeEmail(){
        boolean check=false;
        String newEmail=null;
        while (!check) {
            try {


                System.out.println("Enter new Email:");
                newEmail = Validate.validateEmail(scanner.nextLine());
                for (int i = 0; i < users.size(); i++) {
                    if (username.equals(users.get(i).getUsername())) {
                        if (!newEmail.equals(users.get(i).getEmail())) {
                            users.get(i).setUsername(newEmail);
                            System.out.println("Change Email Success");
                        } else {
                            throw new RuntimeException("Email Already exist");
                        }
                    }
                }
                check = true;
            } catch (RuntimeException e) {
                System.out.println(e.getMessage() + "Please Enter Email");
            }
        }loginSuccess();
        }
    public void loginFail(){
        Menu.loginFail();
        int choose=scanner.nextInt();
        scanner.nextLine();
        switch (choose){
            case 1:
                System.out.println("Login Again");
                login();
                break;
            case 2:
                forgotPassword();
                break;
        }
    }
    public void forgotPassword(){
        System.out.println("Enter your email:");
        String email=scanner.nextLine();
        int count=0;
        for (int i = 0; i < users.size(); i++) {
            if (username.equals(users.get(i).getUsername())) {
                count++;
                boolean check=false;
                while (!check){
                    try {
                        System.out.println("Enter new email:");
                        String newPassword=Validate.validatePassword(scanner.nextLine());
                        users.get(i).setPassword(newPassword);
                        System.out.println("Change Password Success,Login Again");
                        check=true;
                        login();
                    } catch (RuntimeException e) {
                        System.out.println(e.getMessage()+",Please login again");
                    }
                }
            }
        }if (count==0){
            System.out.println("Email is not correct, enter email again");
            forgotPassword();
        }
    }
    public void createNewAccount() {
        boolean check = false;
        long id = 0;
        String newUsername = null;
        String newEmail = null;
        String newPassword = null;
        while (!check) {
            try {
                id = users.size() + 1;
                System.out.println("Please enter username:");
                newUsername = scanner.nextLine();
                System.out.println("Please enter email:");
                newEmail = Validate.validateEmail(scanner.nextLine());
                System.out.println("Enter password");
                newPassword = Validate.validatePassword(scanner.nextLine());
                for (int i = 0; i < users.size(); i++) {
                    if (newUsername.equals(users.get(i).getUsername())) {
                        throw new RuntimeException("Username Already exist");
                    }
                    if (newEmail.equals(users.get(i).getEmail())) {
                        throw new RuntimeException("Email Already exist");
                    }
                }
                check = true;

            } catch (RuntimeException e) {
                System.out.println(e.getMessage() + ",Please perform again");
            }
        }
        users.add(new User(id, newUsername, newEmail, newPassword));
        System.out.println("Sign up success");
        System.out.println("Login...");
        login();
    }
}
