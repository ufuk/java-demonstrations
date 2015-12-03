package com.ufukuzun.javaprofiler.demo;

import spark.Request;
import spark.Response;
import spark.Route;

public class SaveUserRoute implements Route {

    private UserService userService = new UserService();

    @Override
    public Object handle(Request request, Response response) throws Exception {
        response.header("content-type", "text/plain");

        String username = request.queryParams("username");

        userService.saveNewUser(username);

        return "Saved!";
    }

}
