package hello;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Page {

	private String weatherinfo;

	public String getWeatherinfo() {
		return weatherinfo;
	}

	public void setWeatherinfo(String weatherinfo) {
		this.weatherinfo = weatherinfo;
	}
}