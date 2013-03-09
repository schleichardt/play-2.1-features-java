import play.*;
import play.mvc.*;
import static play.mvc.Results.*;

public class Global extends GlobalSettings {
    @Override
    public Result onError(Http.RequestHeader request, Throwable t) {
        return internalServerError(views.html.errorPage.render(t));
    }
}