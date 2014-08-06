package Service;

import model.GPDAO;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpServlet;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;

public class OpenZTListener extends HttpServlet implements
		ServletContextListener {
	private HashMap<String,GPDAO> gpMap = new HashMap<String,GPDAO>();
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
        MonitorService service = new MonitorService();
        service.run();
	}

    public static void main(String[] args){
        MonitorService service = new MonitorService();
        service.run();
    }
}
