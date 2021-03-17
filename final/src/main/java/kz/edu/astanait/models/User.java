package kz.edu.astanait.models;

import kz.edu.astanait.interfaces.IUser;

import java.util.Date;

public class User implements IUser {
    private int id;
    private String fname;
    private String lname;
    private String email;
    private String password;
    private String role;
    private String year;
    private String major;
    private String group;
    private int club_id;
    private int event_id;
    private int news_id;

    public User(Builder builder) {
        setId(builder.id);
        setEmail(builder.email);
        setPassword(builder.password);
        setFname(builder.fname);
        setLname(builder.lname);
        setRole(builder.role);
        setYear(builder.year);
        setMajor(builder.major);
        setGroup(builder.group);
        setClubId(builder.club_id);
        setEventId(builder.event_id);
        setNewsId(builder.news_id);
    }

    public static class Builder{
        private int id;
        private String fname;
        private String lname;
        private String email;
        private String password;
        private String role;
        private String year;
        private String major;
        private String group;
        private int club_id;
        private int event_id;
        private int news_id;

        public User build()
        {
            return new User(this);
        }

        public Builder setUser(String fname, String lname, String email, String password, String role, String year, String major, String group)
        {
            this.fname = fname;
            this.lname = lname;
            this.email = email;
            this.password = password;
            this.role = role;
            this.year = year;
            this.major = major;
            this.group = group;
            return this;
        }

        public Builder withId(int id) {
            this.id = id;
            return this;
        }
        public Builder clubMod(int club_id) {
            this.club_id = club_id;
            return this;
        }
        public Builder eventMod(int event_id) {
            this.event_id = event_id;
            return this;
        }
        public Builder newsMod(int news_id) {
            this.news_id = news_id;
            return this;
        }
    }


    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setFname(String fname) {
        this.fname = fname;
    }

    @Override
    public String getFname() {
        return fname;
    }

    @Override
    public void setLname(String lname) {
        this.lname = lname;
    }

    @Override
    public String getLname() {
        return lname;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String getRole() {
        return role;
    }

    @Override
    public void setClubId(int club_id) {
        this.club_id = club_id;
    }

    @Override
    public int getClubId() {
        return club_id;
    }

    @Override
    public void setEventId(int event_id) {
        this.event_id = event_id;
    }

    @Override
    public int getEventId() {
        return event_id;
    }

    @Override
    public void setNewsId(int news_id) {
        this.news_id = news_id;
    }

    @Override
    public int getNewsId() {
        return news_id;
    }

    @Override
    public void setYear(String year) {
        this.year = year;
    }

    @Override
    public String getYear() {
        return year;
    }

    @Override
    public void setMajor(String major) {
        this.major = major;
    }

    @Override
    public String getMajor() {
        return major;
    }

    @Override
    public void setGroup(String group) {
        this.group = group;
    }

    @Override
    public String getGroup() {
        return group;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", fname='" + fname + '\'' +
                ", lname='" + lname + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
