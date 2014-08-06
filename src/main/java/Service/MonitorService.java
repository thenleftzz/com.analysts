package Service;

import model.GPDAO;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Xerxes on 2014/8/6.
 */
public class MonitorService {
    private Map<String,GPDAO> gpMap = new HashMap<String,GPDAO>();
    private Map<String,GPDAO> showMap = new HashMap<String,GPDAO>();
    private boolean hasChanged =false;

    private String urlStr = "http://qt.gtimg.cn/q=";

    public void run(){
        initData();
        refresh();
    }

    private void initData(){
        File f = new File("src/main/resources/table.csv");
        String args[];
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(f),"GBK"));
            String line = "";
            gpMap.clear();
            while((line=br.readLine())!=null) {
                args = line.split(",");
                generateMap(args,gpMap);
            }
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void generateMap(String[] args,Map<String,GPDAO> gpData){
        GPDAO gpdao = new GPDAO();
        gpdao.setCode(args[0]);
        gpdao.setName(args[1]);
        gpData.put(gpdao.getCode(),gpdao);
    }

    private void refresh(){
        while (true){
            if(gpMap!=null && gpMap.size()>0){
                for(Map.Entry<String,GPDAO> entry:gpMap.entrySet()){
                    GPDAO gpdao = requestURL(entry.getValue());
                    update(gpdao);
                }

                if(hasChanged){
                    printData(showMap);
                    hasChanged = false;
                }

            }else{
                System.err.println("股票代码列表为空!");
            }
        }
    }

    /**
     * 访问网络数据
     * @param gpdao
     * @return
     */
    private GPDAO requestURL(GPDAO gpdao){
        URL url;
        try {
            url = new URL(urlStr+gpdao.getCode().toLowerCase());
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
                temp = sTotalString.split("~");
                gpdao.setClose(temp[3]);
                gpdao.setOpen(temp[5]);
                gpdao.setHeigh(temp[33]);
                gpdao.setLow(temp[34]);
                gpdao.setZt(temp[47]);
                gpdao.setDt(temp[48]);

                return gpdao;
            }
        } catch (Exception e) {
            System.err.println(gpdao.getCode());
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 判断数据是否符合标准
     */
    private void update(GPDAO gpdao){
        if(!hasData(gpdao)&&businessJudge(gpdao)){
            showMap.put(gpdao.getCode(),gpdao);
            //添加第一次开板时间
            Calendar c = Calendar.getInstance();
            gpdao.setFirstOpenTime(c.get(Calendar.HOUR_OF_DAY)+":"+c.get(Calendar.MINUTE)+":"+c.get(Calendar.SECOND));
            hasChanged = true;
        }

    }

//    业务判断逻辑
    private boolean businessJudge(GPDAO gpdao){
        if(gpdao.getHeigh().equals(gpdao.getZt())
                && Float.parseFloat(gpdao.getClose())<Float.parseFloat(gpdao.getHeigh())){
            gpdao.setOpenZT(true);

            return true;
        }

        return false;
    }

    /**
     * 判断数据是否存在
     */
    private boolean hasData(GPDAO gpdao){
        if(showMap.containsKey(gpdao.getCode())){
            return true;
        }

        return false;
    }

    private void printData(Map<String,GPDAO> dataMap){
        System.err.println("=================================");
        for(Map.Entry<String,GPDAO> entry:dataMap.entrySet()){
            System.err.println("股票："+entry.getValue().getName()+"["+entry.getKey()+"]");
        }
    }
}
