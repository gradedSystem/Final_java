package kz.edu.astanait.controllers;

import kz.edu.astanait.JDBC.DB;
import kz.edu.astanait.interfaces.IController;
import kz.edu.astanait.models.Club;
import kz.edu.astanait.models.Moder;
import kz.edu.astanait.models.User;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class ClubController implements IController<Club> {
    DB db = new DB();

    @Override
    public void add(Club club) {
        String sql = "INSERT INTO clubs(name, owner, description, img_url)" +
                "VALUES(?,?,?,?)";

        try {
            PreparedStatement stmt = db.getConnection().prepareStatement(sql);

            stmt.setString(1, club.getName());
            stmt.setString(2, club.getOwner());
            stmt.setString(3, club.getDescription());
            stmt.setString(4, club.getImg_url());

            stmt.execute();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    @Override
    public void update(Club club) {
        String sql = "update clubs set name = ?, owner = ?, description = ?, img_url = ?" +
                " where club_id = ?";
        PreparedStatement stmt = null;
        try {

            stmt = db.getConnection().prepareStatement(sql);

            stmt.setString(1, club.getName());
            stmt.setString(2, club.getOwner());
            stmt.setString(3, club.getDescription());
            stmt.setString(4, club.getImg_url());
            stmt.setInt(5, club.getId());

            stmt.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void delete(Club club) {
        String sql = "delete from clubs where club_id = ?";
        PreparedStatement stmt = null;
        try {

            stmt = db.getConnection().prepareStatement(sql);

            stmt.setInt(1, club.getId());

            stmt.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public List<Club> getAll() {
        List<Club> clubList = new LinkedList<>();
        List<Moder> moderators = new LinkedList<>();
        try {
            Statement statement = db.getConnection().createStatement();
            ResultSet rsModers = statement.executeQuery("SELECT users.user_id,fname,lname,email" +
                    ",password,role,year,major,group_name, cm.club_id from users" +
                    " join club_moders cm on users.user_id = cm.user_id" +
                    " group by cm.club_id");
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
                        .clubMod(rsModers.getInt("club_id")).build();
                moderators.add(moder);
            }
            ResultSet rs = statement.executeQuery("SELECT * FROM clubs");
            Club c;
            while (rs.next()) {
                c = new Club.Builder().setClub(
                        rs.getString("name"),
                        rs.getString("owner"),
                        moderators,
                        rs.getString("description"),
                        rs.getString("img_url")
                ).setClub_id(rs.getInt("club_id")).build();
                clubList.add(c);
            }
            rs.close();
            statement.close();

            return clubList;

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return null;
    }

    @Override
    public Club getById(int id) {

        List<Moder> moderators = new LinkedList<>();
        try {
            PreparedStatement pstmtClubs = db.getConnection().prepareStatement("SELECT * FROM clubs where club_id = ?");
            pstmtClubs.setInt(1, id);
            PreparedStatement pstmtModers = db.getConnection().prepareStatement("SELECT user_id,fname,lname,email" +
                    ",password,role,year,major,group_name,cm.club_id from users" +
                    " join club_moders cm on users.user_id = cm.user_id" +
                    " where club_id = ?" +
                    " group by cm.club_id");
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
                        .clubMod(rsModers.getInt("club_id")).build();
                moderators.add(moder);
            }
            ResultSet rsClubs = pstmtClubs.executeQuery();
            Club c = new Club.Builder().setClub(
                    rsClubs.getString("name"),
                    rsClubs.getString("owner"),
                    moderators,
                    rsClubs.getString("description"),
                    rsClubs.getString("img_url")).setClub_id(rsClubs.getInt("club_id")).build();
            return c;
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return null;
    }
}
