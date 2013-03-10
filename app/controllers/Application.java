package controllers;

import play.*;
import play.mvc.*;

import views.html.*;

public class Application extends Controller {
  
    public static Result index() {
        return ok(index.render("Your new application is ready."));
    }

    public static Result throwError() {
        throw new RuntimeException("causing an error on purpose");
    }

    public static Result showForm() {
        return ok(views.html.csrfProtectedForm.render());
    }

    public static Result parseForm() {
        return TODO;
    }
}
