package hello;

import org.springframework.web.client.RestTemplate;

public class ApplicationTemplate {
	public static void main(String args[]) {
        RestTemplate restTemplate = new RestTemplate();
//        Page page = restTemplate.getForObject("http://m.weather.com.cn/data/101010100.html", Page.class);
//        String result = restTemplate.getForObject("http://example.com/hotels/42/bookings/21", String.class);
//        System.err.println(result);
//        System.out.println("Name:    " + page.getWeatherinfo());
     
        String page = restTemplate.getForObject("http://table.finance.yahoo.com/table.csv?s=000001.sz", String.class);
        System.err.println(page);
    }
}
