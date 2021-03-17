package kz.edu.astanait.models;

import kz.edu.astanait.interfaces.IECN;

import java.util.Date;
import java.util.List;

public class Club implements IECN {
    private int id;
    private String name;
    private String owner;
    private List<Moder> moderators;
    private String description;
    private String img_url;
    private Date created_date;

    public Club(Builder builder) {
        setId(builder.id);
        setName(builder.name);
        setOwner(builder.owner);
        setModerators(builder.moderators);
        setImg_url(builder.img_url);
        setDescription(builder.description);
    }

    public static class Builder {
        private int id;
        private String name;
        private String owner;
        private List<Moder> moderators;
        private String description;
        private String img_url;

        public Club build() {
            return new Club(this);
        }

        public Builder setClub(String name, String owner, List<Moder> moderators, String description, String img_url) {
            this.name = name;
            this.owner = owner;
            this.moderators = moderators;
            this.description = description;
            this.img_url = img_url;
            return this;
        }

        public Builder setClub_id(int club_id) {
            this.id = club_id;
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
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setOwner(String owner) {
        this.owner = owner;
    }

    @Override
    public String getOwner() {
        return owner;
    }

    @Override
    public void setModerators(List<Moder> moderators) {
        this.moderators = moderators;
    }

    @Override
    public List<Moder> getModerators() {
        return moderators;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    @Override
    public String getImg_url() {
        return img_url;
    }

}
