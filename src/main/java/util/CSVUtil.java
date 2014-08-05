package util;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import model.dao.GPDAO;

public class CSVUtil {


	/**
	 * 
	 * @return
	 */
	private static Map<String,GPDAO> initMap(){
		Map<String,GPDAO> returnMap = new HashMap<String,GPDAO>();
		File f = new File("src/main/resources/table.csv");
        
        String args[];
		try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(f),"GBK"));
            String line = "";
            while((line=br.readLine())!=null) {
                args = line.split(",");
                generateMap(args,returnMap);
            }
            br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
            e.printStackTrace();
        }

        return returnMap;
	}

    /**
     * 生成映射类
     * @param args
     * @param maps
     */
    private static void generateMap(String[] args,Map<String,GPDAO> maps){
        GPDAO gpdao = new GPDAO();
        gpdao.setCode(args[0]);
        gpdao.setName(args[1]);
        maps.put(gpdao.getCode(),gpdao);
    }

    private static void updateState(Map<String,GPDAO> maps){
        String urlStr = "http://qt.gtimg.cn/q=";
        if(maps!=null && maps.size()>0){
            for(Map.Entry<String,GPDAO> entry:maps.entrySet()){
                requestURL(urlStr,entry.getValue());
                if(entry.getValue().isOpenZT()){
                    Calendar c = Calendar.getInstance();
                    System.out.println(entry.getValue().getName()+"["+entry.getKey()+"],时间："
                            +c.get(Calendar.HOUR_OF_DAY)+":"
                            +c.get(Calendar.MINUTE)+":"
                            +c.get(Calendar.SECOND));
                }
            }
        }
    }

    private static void requestURL(String strUrl,GPDAO gpdao){
        URL url;
        try {
            url = new URL(strUrl+gpdao.getCode().toLowerCase());
            URLConnection urlConnection = url.openConnection();
            HttpURLConnection httpURLConnection = (HttpURLConnection) urlConnection;
            int responseCode = httpURLConnection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                String temp[];
                InputStream urlStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(
                        new InputStreamReader(urlStream,"gbk"));
                String sCurrentLine = "";
                String sTotalString = "";
                while ((sCurrentLine = bufferedReader.readLine()) != null) {
                    sTotalString += sCurrentLine;
                }
//                System.out.println(sTotalString);
                temp = sTotalString.split("~");
                gpdao.setClose(temp[3]);
                gpdao.setOpen(temp[5]);
                gpdao.setHeigh(temp[33]);
                gpdao.setLow(temp[34]);
                gpdao.setZt(temp[47]);
                gpdao.setDt(temp[48]);

                if(gpdao.getHeigh().equals(gpdao.getZt()) && Float.parseFloat(gpdao.getClose())<Float.parseFloat(gpdao.getHeigh())){
                    gpdao.setOpenZT(true);
                }
            }
        } catch (Exception e) {
            System.err.println(gpdao.getCode());
            e.printStackTrace();
        }
    }

	public static void main(String[] args) {
        Map<String,GPDAO> map = initMap();
        updateState(map);
	}
}
