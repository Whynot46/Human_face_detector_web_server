package com.doslab.human_detector;

public class User {
    private int id;
    private String username;
    private String first_name;
    private String middle_name;
    private String last_name;
    private String password_hash;
    private int role_id;

    public User(int id, String username, String first_name, String middle_name, String last_name, String password_hash,
            int role_id) {
        this.id = id;
        this.username = username;
        this.first_name = first_name;
        this.middle_name = middle_name;
        this.last_name = last_name;
        this.password_hash = password_hash;
        this.role_id = role_id;
    }

    public int get_id() {
        return id;
    }

    public String get_username() {
        return username;
    }

    public String get_fullname() {
        return first_name + " " + middle_name + " " + last_name;
    }

    public String get_first_name() {
        return first_name;
    }

    public String get_middle_name() {
        return middle_name;
    }

    public String get_last_name() {
        return last_name;
    }

    public String get_password_hash() {
        return password_hash;
    }

    public int get_role_id() {
        return role_id;
    }

    public String get_role_name() {
        Role role = DataBase.get_role(role_id);
        return role.get_name();
    }
}