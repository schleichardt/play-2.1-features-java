package controllers.admin;
import controllers.AssetsBuilder;
import play.api.mvc.AnyContent;
import play.api.mvc.Action;
import play.mvc.*;

public class Application extends Controller {

    public static Result index() {
        return ok(views.html.admin.index.render());
    }

    private static AssetsBuilder delegate = new AssetsBuilder();

    public static Action<AnyContent> asset(String path, String file) {
        return delegate.at(path, file);
    }

}