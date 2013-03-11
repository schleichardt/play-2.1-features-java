import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import com.typesafe.config.impl.ConfigImpl;
import play.*;
import play.api.mvc.EssentialFilter;
import play.mvc.*;
import static play.mvc.Results.*;

import play.filters.csrf.CSRFFilter;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;

public class Global extends GlobalSettings {
    @Override
    public Result onError(Http.RequestHeader request, Throwable t) {
        return internalServerError(views.html.errorPage.render(t));
    }

    @Override
    public <T extends EssentialFilter> Class<T>[] filters() {
        return  new Class[]{CSRFFilter.class};
    }

    @Override
    public Configuration onLoadConfig(Configuration configuration, File file, ClassLoader classLoader) {
        Configuration resultConf = configuration;
        if (configuration.getInt("embedmongo.port") == null) {
            Config config = ConfigFactory.parseString("embedmongo.port=" + getFreePort());
            //there is a Typesafe config wrapped in Play Scala, wrapped in Play Java
            //merging configurations
            resultConf = new Configuration(config.withFallback(configuration.getWrappedConfiguration().underlying()));
        }
        return resultConf;
    }

    private int getFreePort() {
        ServerSocket socket = null;
        try {
            socket = new ServerSocket(0);
            return socket.getLocalPort();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if(socket != null)
                try {
                    socket.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
        }
    }
}