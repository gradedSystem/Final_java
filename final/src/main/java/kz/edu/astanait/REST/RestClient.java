package kz.edu.astanait.REST;

import com.google.gson.Gson;
import kz.edu.astanait.models.User;
import org.glassfish.jersey.client.ClientConfig;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class RestClient {
    private WebTarget getWebTarget(String type,String param) {
        ClientConfig config = new ClientConfig();
        Client client = ClientBuilder.newClient(config);
        System.out.println("http://localhost:8080/finalADJ_war_exploded/api/byGroup/"+param);
        switch (type) {
            case "byGroup":
                return client.target("http://localhost:8080/finalADJ_war_exploded/api/byGroup/"+param);
            case "byYear":
                return client.target("http://localhost:8080/finalADJ_war_exploded/api/byYear/"+param);
            case "byMajor":
                return client.target("http://localhost:8080/finalADJ_war_exploded/api/byMajor/"+param);
            case "byName":
                return client.target("http://localhost:8080/finalADJ_war_exploded/api/users/"+param);
            case "showAll":
                return client.target("http://localhost:8080/finalADJ_war_exploded/api/users/all");
        }
        return null;
    }

    public List<User> getByGroup(String group) {
        WebTarget target = getWebTarget("byGroup",group);
        String json = null;
        if (target != null) {
            json = target.request().accept(MediaType.APPLICATION_JSON).get(String.class);
        }

        Gson gson = new Gson();
        User[] users = gson.fromJson(json, User[].class);
        return new LinkedList<>(Arrays.asList(users));
    }
    public List<User> getByYear(String year) {
        WebTarget target = getWebTarget("byYear",year);
        String json = null;
        if (target != null) {
            json = target.request().accept(MediaType.APPLICATION_JSON).get(String.class);
        }

        Gson gson = new Gson();
        User[] users = gson.fromJson(json, User[].class);
        return new LinkedList<>(Arrays.asList(users));
    }
    public List<User> getByMajor(String major) {
        WebTarget target = getWebTarget("byMajor",major);
        String json = null;
        if (target != null) {
            json = target.request().accept(MediaType.APPLICATION_JSON).get(String.class);
        }

        Gson gson = new Gson();
        User[] users = gson.fromJson(json, User[].class);
        return new LinkedList<>(Arrays.asList(users));
    }
    public List<User> getByName(String name) {
        WebTarget target = getWebTarget("byName", name);
        String json = null;
        if (target != null) {
            json = target.request().accept(MediaType.APPLICATION_JSON).get(String.class);
        }

        Gson gson = new Gson();
        User[] users = gson.fromJson(json, User[].class);
        return new LinkedList<>(Arrays.asList(users));
    }
    public List<User> getAll() {
        WebTarget target = getWebTarget("showAll","");
        String json = null;
        if (target != null) {
            json = target.request().accept(MediaType.APPLICATION_JSON).get(String.class);
        }

        Gson gson = new Gson();
        User[] users = gson.fromJson(json, User[].class);
        return new LinkedList<>(Arrays.asList(users));
    }
}
