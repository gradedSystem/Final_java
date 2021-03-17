package kz.edu.astanait.models;

public class Moder extends User {
    public Moder(Builder builder) {
        super(builder);
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
    public static class Builder extends User.Builder {
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

        public Moder build()
        {
            return new Moder(this);
        }

        public Moder.Builder setUser(String fname, String lname, String email, String password, String role, String year, String major, String group)
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

        public Moder.Builder withId(int id) {
            this.id = id;
            return this;
        }
        public Moder.Builder clubMod(int club_id) {
            this.club_id = club_id;
            return this;
        }
        public Moder.Builder eventMod(int event_id) {
            this.event_id = event_id;
            return this;
        }
        public Moder.Builder newsMod(int news_id) {
            this.news_id = news_id;
            return this;
        }
    }

}
