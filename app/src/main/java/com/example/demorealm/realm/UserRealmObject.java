package com.example.demorealm.realm;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class UserRealmObject extends RealmObject {

    @PrimaryKey
    String id;
    String userName;

    public UserRealmObject() {
    }

    String userAge;

    public UserRealmObject(String id, String userName, String userAge) {
        this.id = id;
        this.userName = userName;
        this.userAge = userAge;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserAge() {
        return userAge;
    }

    public void setUserAge(String userAge) {
        this.userAge = userAge;
    }
}
