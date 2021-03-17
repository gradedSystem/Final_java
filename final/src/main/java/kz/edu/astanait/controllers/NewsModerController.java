package kz.edu.astanait.controllers;

import kz.edu.astanait.JDBC.DB;
import kz.edu.astanait.interfaces.IController;
import kz.edu.astanait.models.Moder;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class NewsModerController implements IController<Moder> {
    DB db = new DB();

    public void deleteAll(int news_id) {
        String sql = "delete from news_moders where news_id = ?";
        PreparedStatement stmt = null;
        try {

            stmt = db.getConnection().prepareStatement(sql);

            stmt.setInt(1, news_id);

            stmt.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    @Override
    public void add(Moder moder) {
        String sql = "INSERT INTO news_moders(user_id, news_id)" +
                "VALUES(?,?)";
        String sql2 = "update users set role = 'news moderator' where user_id = ?";

        try {
            PreparedStatement stmt = db.getConnection().prepareStatement(sql);
            PreparedStatement stmt2 = db.getConnection().prepareStatement(sql2);
            stmt2.setInt(1,moder.getId());
            stmt.setInt(1, moder.getId());
            stmt.setInt(2, moder.getNewsId());

            stmt.execute();
            stmt2.execute();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    @Override
    public void update(Moder moder) {
        String sql = "update news_moders set news_id = ?" +
                " where user_id = ?";
        PreparedStatement stmt = null;
        try {

            stmt = db.getConnection().prepareStatement(sql);

            stmt.setInt(1, moder.getNewsId());
            stmt.setInt(2, moder.getId());

            stmt.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void delete(Moder moder) {
        String sql = "delete from news_moders where user_id = ?";
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
