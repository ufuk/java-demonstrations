package com.ufukuzun.javaprofiler.demo;

import spark.Route;

import static spark.Spark.get;

public class CallTreeDemo {

    public static void main(String... args) {
        get("/", (request, response) -> {
            response.header("content-type", "text/plain");

            return "Hello VisualVM!";
        });

        get("/save", new SaveUserRoute());
    }

}
