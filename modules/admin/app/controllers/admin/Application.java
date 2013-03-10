package controllers.admin;

import controllers.AssetsBuilder;
import play.mvc.*;
import play.mvc.Result;

public class Application extends Controller {

    public static Result index() {
        return ok(views.html.admin.index.render());
    }
    private static AssetsBuilder delegate = new AssetsBuilder();

    public static play.api.mvc.Action<play.api.mvc.AnyContent> asset(String path, String file) {
        return delegate.at(path, file);
    }
}
