package com.authentication;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.lang.reflect.Type;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


public class Repository {

    public List<User>users;
    public List<User>getDataGSON(){
        try {
            FileReader reader=new FileReader("user.json");
            Type objType=new TypeToken<List<User>>(){

            }.getType();
            users=new Gson().fromJson(reader,objType);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return users;
    }
}
