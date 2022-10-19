package whu.xzy;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jsoup.nodes.Document;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
public class test000001 {


    public static void main(String[] args) throws Exception {
        String path ="/Users/autt/Desktop/";
        String url = "http://cs.whu.edu.cn/teacher.aspx?showtype=jobtitle&typename=%e6%95%99%e6%8e%88";
        WriteExcel(path,getResoucrce(url));
    }

    private static TeacherInformation[] getResoucrce(String url) throws IOException {
        Document document = Jsoup.parse(new URL(url), 30000);
        Elements elements = document.getElementsByTag("tr");
        TeacherInformation[] Infos = new TeacherInformation[elements.size()];
        for (int i =0;i< elements.size();i++) {
            Element el = elements.get(i);
                String name =  el.getElementsByClass("w1").text();
                String sex  =  el.getElementsByClass("w2").text();
                String title = el.getElementsByClass("w4").text();
                String field=  el.getElementsByClass("w5").text();
            TeacherInformation Info = new TeacherInformation(name,sex,title,field);
            Infos[i] = Info;
        }
        return Infos;
    }

    public static void WriteExcel(String path, TeacherInformation[] Infos) throws Exception {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("sheet01");
//        Row row000 = sheet.createRow(3);
//        row000.createCell(0).setCellValue("姓名");
//        row000.createCell(1).setCellValue("性别");
//        row000.createCell(2).setCellValue("职称");
//        row000.createCell(3).setCellValue("方向");


        //创建行并导入每行数据
        for (int i = 0; i < Infos.length; i++) {
            Row row = sheet.createRow(i);
            Cell cell01 = row.createCell(0);
            cell01.setCellValue(Infos[i].getName());
            Cell cell02 = row.createCell(1);
            cell02.setCellValue(Infos[i].getSex());
            Cell cell03 = row.createCell(2);
            cell03.setCellValue(Infos[i].getTitle());
            Cell cell04 = row.createCell(3);
            cell04.setCellValue(Infos[i].getField());
        }
        System.out.println("over");
        //获取io流
        FileOutputStream fos = new FileOutputStream(path + "教师信息.xlsx");
        //生成一张表
        workbook.write(fos);
        fos.close();
    }

}
class TeacherInformation{
    String Name;
    String Sex;
    String Title;
    String Field;

    public TeacherInformation(String name, String sex, String title, String field) {
        Name = name;
        Sex = sex;
        Title = title;
        Field = field;
    }

    public String getName() {
        return Name;
    }

    public String getSex() {
        return Sex;
    }

    public String getTitle() {
        return Title;
    }

    public String getField() {
        return Field;
    }
}



