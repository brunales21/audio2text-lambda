package com.brunales;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class Handler implements RequestHandler<Handler.Request, Handler.Response> {
    private static final String LOG_LEVEL = System.getenv().getOrDefault("LOG_LEVEL", "INFO");

    @Override
    public Response handleRequest(Request input, Context context) {
        context.getLogger().log("LOG_LEVEL=" + LOG_LEVEL);
        return new Response("ok", "Hello " + (input != null ? input.getName() : "world"));
    }

    public static class Request { private String name; public String getName(){ return name; } public void setName(String n){ this.name=n; } }
    public static class Response { private String status; private String message;
        public Response(String s, String m){ this.status=s; this.message=m; }
        public String getStatus(){ return status; }
        public String getMessage(){ return message; }
    }
}
