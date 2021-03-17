package kz.edu.astanait.REST;

import kz.edu.astanait.controllers.UserController;
import kz.edu.astanait.models.User;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

@Path("/byMajor")
public class MajorRest {
    private final UserController userController = new UserController();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{param}")
    public Response getByMajor(@PathParam("param") String major){
        ArrayList<User> u;
        try {
            u = userController.getByMajor(major);
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
