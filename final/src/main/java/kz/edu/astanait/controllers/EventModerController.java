package kz.edu.astanait.controllers;

import kz.edu.astanait.JDBC.DB;
import kz.edu.astanait.interfaces.IController;
import kz.edu.astanait.models.Event;
import kz.edu.astanait.models.Moder;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class EventModerController implements IController<Moder> {
    DB db = new DB();

    public void deleteAll(int event_id) {
        String sql = "delete from event_moders where event_id = ?";
        PreparedStatement stmt = null;
        try {

            stmt = db.getConnection().prepareStatement(sql);

            stmt.setInt(1, event_id);

            stmt.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    @Override
    public void add(Moder moder) {
        String sql = "INSERT INTO event_moders(user_id, event_id)" +
                "VALUES(?,?)";
        String sql2 = "update users set role = 'event moderator' where user_id = ?";

        try {
            PreparedStatement stmt = db.getConnection().prepareStatement(sql);
            PreparedStatement stmt2 = db.getConnection().prepareStatement(sql2);
            stmt2.setInt(1,moder.getId());
            stmt.setInt(1, moder.getId());
            stmt.setInt(2, moder.getEventId());

            stmt.execute();
            stmt2.execute();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    @Override
    public void update(Moder moder) {
        String sql = "update event_moders set event_id = ?" +
                " where user_id = ?";
        PreparedStatement stmt = null;
        try {

            stmt = db.getConnection().prepareStatement(sql);

            stmt.setInt(1, moder.getEventId());
            stmt.setInt(2, moder.getId());

            stmt.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void delete(Moder moder) {
        String sql = "delete from event_moders where user_id = ?";
        PreparedStatement stmt = null;
        try {

            stmt = db.getConnection().prepareStatement(sql);

            stmt.setInt(1, moder.getId());

            stmt.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public List<Moder> getAll() {
        return null;
    }

    @Override
    public Moder getById(int id) {

        return null;
    }
}
