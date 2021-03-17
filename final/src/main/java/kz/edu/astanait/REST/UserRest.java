package kz.edu.astanait.REST;

import kz.edu.astanait.controllers.UserController;
import kz.edu.astanait.models.User;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

@Path("/users")
public class UserRest {
    private final UserController userController = new UserController();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{param}")
    public Response getByName(@PathParam("param") String name){
        ArrayList<User> u;
        String[] fullName =  name.split(" ");
        String fname = fullName[0];
        String lname = fullName[1];
        try {
            u = userController.getByName(fname,lname);
        } catch (ServerErrorException ex) {
            return Response.serverError().build();
        } catch (BadRequestException ex) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        if (u == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } else {
            return Response.ok(u).build();
        }
    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/all")
    public Response getAll(){
        ArrayList<User> u;
        try {
            u = (ArrayList<User>) userController.getAll();
        } catch (ServerErrorException ex) {
            return Response.serverError().build();
        } catch (BadRequestException ex) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        if (u == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } else {
            return Response.ok(u).build();
        }
    }
}
