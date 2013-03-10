import play.*;
import play.api.mvc.EssentialFilter;
import play.mvc.*;
import static play.mvc.Results.*;

import play.filters.csrf.CSRFFilter;

public class Global extends GlobalSettings {
    @Override
    public Result onError(Http.RequestHeader request, Throwable t) {
        return internalServerError(views.html.errorPage.render(t));
    }

    @Override
    public <T extends EssentialFilter> Class<T>[] filters() {
        return  new Class[]{CSRFFilter.class};
    }
}