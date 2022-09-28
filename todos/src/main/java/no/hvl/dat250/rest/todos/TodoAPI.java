package no.hvl.dat250.rest.todos;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

import static spark.Spark.*;

/**
 * Rest-Endpoint.
 */
public class TodoAPI {
    //private static final AtomicLong count = new AtomicLong(0);
    public static void main(String[] args) {
        if (args.length > 0) {
            port(Integer.parseInt(args[0]));
        } else {
            port(6000);
        }
        after((req, res) -> res.type("application/json"));

        // TODO: Implement API, such that the testcases succeed.
        Set<Todo> todos = new HashSet<>();

        get(
                "/todos",
                (req,res)->
                {
                    Gson gson = new Gson();
                    return gson.toJson(todos);
                }
        );
        get(
                "/todos/:id",
                (req,res)->
                {
                    Gson gson = new Gson();
                    for (Todo todo : todos){
                        if ( req.params(":id").equals(todo.getId().toString())) {
                    return todo.toJson();
                }}

                    return "id not found";
                }
        );
        put(
                "/todos",
                (req,res)->
                {
                    Todo todo = new Todo(null,null);
                    Gson gson = new Gson();
                    todo = gson.fromJson(req.body(),Todo.class);
                    todos.add(todo);
                    return todo.toJson();
                }
        );
        post(
                "/todos",
                (req,res)->
                {
                    Todo todo = new Todo(null,null);
                    Gson gson = new Gson();
                    todo = gson.fromJson(req.body(),Todo.class);

                    todos.add(todo);
                    return todo.toJson();
                }

        );

    }
}
