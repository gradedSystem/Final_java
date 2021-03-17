package kz.edu.astanait.controllers;

import kz.edu.astanait.JDBC.DB;
import kz.edu.astanait.interfaces.IController;
import kz.edu.astanait.models.Moder;
import kz.edu.astanait.models.News;
import kz.edu.astanait.models.User;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class NewsController implements IController<News> {
    DB db = new DB();

    @Override
    public void add(News news) {
        String sql = "INSERT INTO news(name, owner, description, img_url)" +
                "VALUES(?,?,?,?)";

        try {
            PreparedStatement stmt = db.getConnection().prepareStatement(sql);

            stmt.setString(1, news.getName());
            stmt.setString(2, news.getOwner());
            stmt.setString(3, news.getDescription());
            stmt.setString(4, news.getImg_url());

            stmt.execute();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    @Override
    public void update(News news) {
        String sql = "update news set name = ?, owner = ?, description = ?, img_url = ? " +
                " where news_id = ?";
        PreparedStatement stmt = null;
        try {

            stmt = db.getConnection().prepareStatement(sql);

            stmt.setString(1, news.getName());
            stmt.setString(2, news.getOwner());
            stmt.setString(3, news.getDescription());
            stmt.setString(4, news.getImg_url());
            stmt.setInt(5, news.getId());

            stmt.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void delete(News news) {
        String sql = "delete from news where news_id = ?";
        PreparedStatement stmt = null;
        try {

            stmt = db.getConnection().prepareStatement(sql);

            stmt.setInt(1, news.getId());

            stmt.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public List<News> getAll() {
        List<News> newsList = new LinkedList<>();
        List<Moder> moderators = new LinkedList<>();
        try {
            Statement statement = db.getConnection().createStatement();
            ResultSet rsModers = statement.executeQuery("SELECT users.user_id,fname,lname,email" +
                    ",password,role,year,major,group_name,nm.news_id from users" +
                    " join news_moders nm on users.user_id = nm.user_id" +
                    " group by nm.news_id");
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
                        .eventMod(rsModers.getInt("news_id")).build();
                moderators.add(moder);
            }
            ResultSet rs = statement.executeQuery("SELECT * FROM news");
            News n;
            while (rs.next()) {
                n = new News.Builder().setNews(
                        rs.getString("name"),
                        rs.getString("owner"),
                        moderators,
                        rs.getString("description"),
                        rs.getString("img_url")
                ).setNews_id(rs.getInt("news_id")).build();
                newsList.add(n);
            }
            rs.close();
            statement.close();

            return newsList;

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return null;
    }

    @Override
    public News getById(int id) {

        List<Moder> moderators = new LinkedList<>();
        try {
            PreparedStatement pstmtClubs = db.getConnection().prepareStatement("SELECT * FROM news where news_id = ?");
            pstmtClubs.setInt(1, id);
            PreparedStatement pstmtModers = db.getConnection().prepareStatement("SELECT user_id,fname,lname,email" +
                    ",password,role,year,major,group_name,nm.news_id from users" +
                    " join news_moders nm on users.user_id = nm.user_id" +
                    " where news_id = ?" +
                    " group by nm.news_id");
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
                        .newsMod(rsModers.getInt("news_id")).build();
                moderators.add(moder);
            }
            ResultSet rsNews = pstmtClubs.executeQuery();
            News n = new News.Builder().setNews(
                    rsNews.getString("name"),
                    rsNews.getString("owner"),
                    moderators,
                    rsNews.getString("description"),
                    rsNews.getString("img_url")
            ).setNews_id(rsModers.getInt("news_id")).build();
            return n;
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return null;
    }
}
