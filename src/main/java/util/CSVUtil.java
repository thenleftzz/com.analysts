package util;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import model.dao.GPDAO;

public class CSVUtil {
	/**
	 * 
	 * @return
	 */
	public static Map<String,GPDAO> initMap(){
		Map<String,GPDAO> returnMap = new HashMap<String,GPDAO>();
		File f = new File("src/main/resources/table.csv");
        String args[];
		try {
            BufferedReader br = new BufferedReader(new FileReader(f));
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
        maps.put();
    }
	public static void main(String[] args) {
		initMap();
	}
}
