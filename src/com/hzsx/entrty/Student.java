package com.hzsx.entrty;

public class Student {

    int id;
    String username;
    String  userage;

    public Student() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserage() {
        return userage;
    }

    public void setUserage(String userage) {
        this.userage = userage;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", userage='" + userage + '\'' +
                '}';
    }

    public Student(int id, String username, String userage) {
        this.id = id;
        this.username = username;
        this.userage = userage;
    }
}
