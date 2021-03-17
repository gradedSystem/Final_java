package kz.edu.astanait.interfaces;

import java.util.Date;

public interface IUser {
    public void setId(int id);
    public int getId();

    public void setFname(String fname);
    public String getFname();

    public void setLname(String lname);
    public String getLname();

    public void setEmail(String email);
    public String getEmail();

    public void setPassword(String password);
    public String getPassword();

    public void setRole(String role);
    public String getRole();

    public void setClubId(int club_id);
    public int getClubId();

    public void setEventId(int event_id);
    public int getEventId();

    public void setNewsId(int news_id);
    public int getNewsId();

    public void setYear(String year);
    public String getYear();

    public void setMajor(String major);
    public String getMajor();

    public void setGroup(String group);
    public String getGroup();
}
