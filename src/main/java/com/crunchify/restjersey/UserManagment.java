package com.crunchify.restjersey;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lukasz.homik on 2017-01-04.
 */
@Path("/userService")
public class UserManagment {
    @GET
//    @Produces(MediaType.APPLICATION_XML)
    @Produces("application/json")
    @Path("{user}")
    public List<User> convertCtoF(@PathParam("user") String name) {
        List<User> userList = new ArrayList<User>();
        User user = new User(1, "Mahesh", "Teacher");
        User user2 = new User(2, "Jeleniosy", "Reaper");
        User user3 = new User(3, name, "Manual");
        userList.add(user);
        userList.add(user2);
        if (name!=null || !name.isEmpty()) {
            userList.add(user3);
        }

        return userList;
    }
    @GET
    @Path("/user/{id}{format:(/format/[^/]+?)?}")
    public Response getUser(
            @PathParam("id") int id,
            @PathParam("format") String format) {
        String responseText = "No response";

        GenericEntity<List<User>> list = new GenericEntity<List<User>>(createUsers()) {};

        if(format == "" || format.isEmpty() || format == null){
        }
        else{
            format = format.split("/")[2];
        }
        if (format.equals("json")) {
            // Optional parameter "format" not specified

            return Response.status(200).type("application/json").entity(list).build();
        }
        else if(format.equals("xml")){
            return Response.status(200).type("application/xml").entity(list).build();
        }
        else{
            return Response.status(200).type("text/plain").entity(responseText).build();
        }
    }

    private List<User> createUsers(){
        List<User> userList = new ArrayList<User>();
        User user = new User(1, "Mahesh", "Teacher");
        User user2 = new User(2, "Jeleniosy", "Reaper");
        userList.add(user);
        userList.add(user2);

        return userList;
    }
}
