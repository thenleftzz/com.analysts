package Service;

import model.dao.GPDAO;

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
//		requestURL() ;
	}

	public void requestURL() {
		String urlStr = "http://qt.gtimg.cn/q=sz000858";
		//String urlStr = "http://q.stock.sohu.com/hisHq?code=cn_300228&start=20130930&end=20131231&stat=1&order=D&period=d&callback=historySearchHandler&rt=jsonp";
		URL url;
		try {
			url = new URL(urlStr);
			URLConnection urlConnection = url.openConnection();
			HttpURLConnection httpURLConnection = (HttpURLConnection) urlConnection;
			int responseCode = httpURLConnection.getResponseCode();
			if (responseCode == HttpURLConnection.HTTP_OK) {
				System.err.println("成功");
				InputStream urlStream = httpURLConnection.getInputStream();
				BufferedReader bufferedReader = new BufferedReader(
						new InputStreamReader(urlStream,"gbk"));
				String sCurrentLine = "";
				String sTotalString = "";
				while ((sCurrentLine = bufferedReader.readLine()) != null) {
					sTotalString += sCurrentLine;
				}
				
				System.err.println(sTotalString);
			} else {
				System.err.println("失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean checkState() {

		return false;
	}

    public boolean updateState(){
        return false;
    }
	
	public static void main(String[] args) {
//		new OpenZTListener().requestURL();
//		1、获取全部列表
//		2、启动状态监听
//		3、启动定时监听
//		4、更新数据状态
	}
}
