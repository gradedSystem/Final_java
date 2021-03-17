package kz.edu.astanait.controllers;

import kz.edu.astanait.JDBC.DB;
import kz.edu.astanait.interfaces.IController;
import kz.edu.astanait.models.Club;
import kz.edu.astanait.models.Event;
import kz.edu.astanait.models.Moder;
import kz.edu.astanait.models.User;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class EventController implements IController<Event> {
    DB db = new DB();

    @Override
    public void add(Event event) {
        String sql = "INSERT INTO events(name, owner, description, img_url)" +
                "VALUES(?,?,?,?)";

        try {
            PreparedStatement stmt = db.getConnection().prepareStatement(sql);

            stmt.setString(1, event.getName());
            stmt.setString(2, event.getOwner());
            stmt.setString(3, event.getDescription());
            stmt.setString(4, event.getImg_url());

            stmt.execute();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    @Override
    public void update(Event event) {
        String sql = "update events set name = ?, owner = ?, description = ?, img_url = ?" +
                " where event_id = ?";
        PreparedStatement stmt = null;
        try {

            stmt = db.getConnection().prepareStatement(sql);

            stmt.setString(1, event.getName());
            stmt.setString(2, event.getOwner());
            stmt.setString(3, event.getDescription());
            stmt.setString(4, event.getImg_url());
            stmt.setInt(5, event.getId());

            stmt.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void delete(Event event) {
        String sql = "delete from events where event_id = ?";
        PreparedStatement stmt = null;
        try {

            stmt = db.getConnection().prepareStatement(sql);

            stmt.setInt(1, event.getId());

            stmt.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public List<Event> getAll() {
        List<Event> eventList = new LinkedList<>();
        List<Moder> moderators = new LinkedList<>();
        try {
            Statement statement = db.getConnection().createStatement();
            ResultSet rsModers = statement.executeQuery("SELECT users.user_id,fname,lname,email" +
                    ",password,role,year,major,group_name,em.event_id from users" +
                    " join event_moders em on users.user_id = em.user_id" +
                    " group by em.event_id");
            Moder moder;
            while (rsModers.next()) {
                moder = new Moder.Builder().setUser(
                        rsModers.getString("fname"),
                        rsModers.getString("lname"),
                        rsModers.getString("email"),
                        rsModers.getString("password"),
                        rsModers.getString("role"),
                        rsModers.getString("year"),
                        rsModers.getString("major"),
                        rsModers.getString("group_name"))
                        .withId(rsModers.getInt("user_id"))
                        .eventMod(rsModers.getInt("event_id")).build();
                moderators.add(moder);
            }
            ResultSet rs = statement.executeQuery("SELECT * FROM events");
            Event e;
            while (rs.next()) {
                e = new Event.Builder().setEvent(
                        rs.getString("name"),
                        rs.getString("owner"),
                        moderators,
                        rs.getString("description"),
                        rs.getString("img_url")).setEvent_id(rs.getInt("event_id")).build();
                eventList.add(e);
            }
            rs.close();
            statement.close();

            return eventList;

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return null;
    }

    @Override
    public Event getById(int id) {

        List<Moder> moderators = new LinkedList<>();
        try {
            PreparedStatement pstmtClubs = db.getConnection().prepareStatement("SELECT * FROM events where event_id = ?");
            pstmtClubs.setInt(1, id);
            PreparedStatement pstmtModers = db.getConnection().prepareStatement("SELECT user_id,fname,lname,email" +
                    ",password,role,year,major,group_name,em.event_id from users" +
                    " join event_moders em on users.user_id = em.user_id" +
                    " where event_id = ?" +
                    " group by em.event_id");
            pstmtModers.setInt(1, id);
            ResultSet rsModers = pstmtClubs.executeQuery();
            Moder moder;
            while (rsModers.next()) {
                moder = new Moder.Builder().setUser(
                        rsModers.getString("fname"),
                        rsModers.getString("lname"),
                        rsModers.getString("email"),
                        rsModers.getString("password"),
                        rsModers.getString("role"),
                        rsModers.getString("year"),
                        rsModers.getString("major"),
                        rsModers.getString("group_name"))
                        .withId(rsModers.getInt("user_id"))
                        .eventMod(rsModers.getInt("event_id")).build();
                moderators.add(moder);
            }
            ResultSet rsEvent = pstmtClubs.executeQuery();
            Event e = new Event.Builder().setEvent(
                    rsEvent.getString("name"),
                    rsEvent.getString("owner"),
                    moderators,
                    rsEvent.getString("description"),
                    rsEvent.getString("img_url")
            ).setEvent_id(rsModers.getInt("club_id")).build();
            return e;
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return null;
    }
}
