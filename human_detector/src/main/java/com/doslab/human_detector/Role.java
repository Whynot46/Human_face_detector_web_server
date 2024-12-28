package com.doslab.human_detector;

public class Role {
    private int id;
    private String name;

    Role(int id, String name) {
        this.id = id;
        this.name = name;
    }

    int get_id() {
        return id;
    }

    String get_name() {
        return name;
    }
}
