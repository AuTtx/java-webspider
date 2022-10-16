package whu.xzy;
/*
* 爬取未来七天的天气数据，并导入到本地excel表格中
* @Author zyt之父xzy
* @qq:1932063838
* */
import org.apache.commons.io.FileUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;

public class pachong {
    private static String url = "http://www.weather.com.cn/html/weather/101280101.shtml";
    public static final int dateNum = 7;
    public static String path = "/Users/autt/Desktop/";

    public static void main(String[] args) throws Exception {
        WriteExcel(path, getResoucrce(url));
    }

    private static Information[] getResoucrce(String url) throws IOException {
        Document document = Jsoup.connect(url).get();//拿到网页资源
        Elements elements = document.select("li[class~=^sky skyid]");//拿到七天信息
        Element[] eles = new Element[dateNum];
        for (int i = 0; i < dateNum; i++) {
            eles[i] = elements.get(i);
        }
        Information[] Infos = new Information[dateNum];
        //导入Infos数组内的信息
        for (int i = 0; i < elements.size(); i++) {
            //获取温度
            Elements elements1 = eles[i].select("[class=tem]");
            String temperature = elements1.get(0).text();
//            System.out.println(temperature);
            //获取日期
            Elements elements2 = eles[i].select("h1");
            String date = elements2.get(0).text();
//            System.out.println(date);
            //获取天气
            Elements elements3 = eles[i].select("[class=wea]");
            String weather = elements3.get(0).text();
//            System.out.println(weather);
            //获取风力
            Elements elements4 = eles[i].select("i");
            String windStrength = elements4.get(1).text();
//            System.out.println(windStrength);

//          Information元素存放每天的天气信息，放在Infos数组里
            Information Info = new Information(date, weather, temperature, windStrength);
            Infos[i] = Info;
        }
        return Infos;
    }


    public static void WriteExcel(String path, Information[] Infos) throws Exception {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("sheet01");
        Row row000 = sheet.createRow(0);
        row000.createCell(0).setCellValue("日期");
        row000.createCell(1).setCellValue("天气情况");
        row000.createCell(2).setCellValue("温度");
        row000.createCell(3).setCellValue("风力大小");
//        //初始化第一行默认字段
//        String[]a = {"日期","天气情况","气温","风力"};
//        Row row1 = sheet.createRow(0);
//        row1.createCell(0).setCellValue("日期");
//        SetRowInfo(row1,4,a);

        //创建行并导入每行数据
        for (int i = 1; i < Infos.length; i++) {
            Row row = sheet.createRow(i);
            Cell cell01 = row.createCell(0);
            cell01.setCellValue(Infos[i].getDate());
            Cell cell02 = row.createCell(1);
            cell02.setCellValue(Infos[i].getWeather());
            Cell cell03 = row.createCell(2);
            cell03.setCellValue(Infos[i].getTemperature());
            Cell cell04 = row.createCell(3);
            cell04.setCellValue(Infos[i].getWindStrength());
        }
        System.out.println("over");
        //获取io流
        FileOutputStream fos = new FileOutputStream(path + "WeatherReport.xlsx");
        //生成一张表
        workbook.write(fos);
        fos.close();
    }
}

//    public static void SetRowInfo(Row row,int cellNum,String[] a){
//        if (!(cellNum==a.length)) {
//            System.out.println("种类个数与单元格字段数不相同！！！");
//        }
//        for (int i = 0; i <= cellNum; i++) {
//            row.createCell(i).setCellValue(a[i]);
//        }
//
//    }
//}

class Information{
    String Date;
    String Weather;
    String Temperature;
    String WindStrength;

    public String getDate() {
        return Date;
    }

    public String getWeather() {
        return Weather;
    }

    public String getTemperature() {
        return Temperature;
    }

    public String getWindStrength() {
        return WindStrength;
    }

    public Information(String date, String weather, String temperature, String windStrength) {
        Date = date;
        Weather = weather;
        Temperature = temperature;
        WindStrength = windStrength;
    }
}
