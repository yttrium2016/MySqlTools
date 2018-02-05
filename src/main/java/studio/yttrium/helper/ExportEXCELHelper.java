package studio.yttrium.helper;

import org.apache.poi.hssf.usermodel.*;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA
 * Created By 杨振宇
 * Date: 2017/7/27
 * Time: 10:30
 */
public class ExportEXCELHelper {


    /**
     * @param title   表格标题名
     * @param headers 表格属性列名数组 （第一行标题）
     * @param col     需要显示的表格属性列名数组 如果是javabean 必须和字段名字一直 如果为Map 必须为Map的key名字对应
     * @param dataMap 需要显示的数据集合,集合泛型支持两种，1：符合javabean风格的类的对象 2：Map类型。此方法支持的
     *                javabean属性的数据类型有基本数据类型及String,Date,byte[](图片数据)
     * @param pattern 如果有时间数据，设定输出格式。默认为"yyyy-MM-dd HH:mm:ss"
     */
    public static HSSFWorkbook exportExcel(String title, String[] headers, String[] col, List<Map<String, Object>> dataMap, String pattern) {
        if (pattern == null || pattern.equals("")) pattern = "yyyy-MM-dd HH:mm:ss";
        // 声明一个工作薄
        HSSFWorkbook workbook = new HSSFWorkbook();
        // 生成一个表格
        HSSFSheet sheet = workbook.createSheet(title);
        // 设置表格默认列宽度为15个字节
        sheet.setDefaultColumnWidth(15);
        // 生成一个样式
        HSSFCellStyle style = workbook.createCellStyle();
        // 设置这些样式
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        // 生成一个字体
        HSSFFont font = workbook.createFont();
        font.setBold(true);
        // 把字体应用到当前的样式
        style.setFont(font);

        // 生成并设置另一个样式
        HSSFCellStyle style2 = workbook.createCellStyle();
        style2.setWrapText(true);
        style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        // 生成另一个字体

        // 把字体应用到当前的样式

        // 声明一个画图的顶级管理器
        HSSFPatriarch patriarch = sheet.createDrawingPatriarch();
        // 产生表格标题行
        HSSFRow row = sheet.createRow(0);
        int Cell = 0;
        for (String header : headers) {
            HSSFCell cell = row.createCell(Cell);
            cell.setCellStyle(style);
            HSSFRichTextString text = new HSSFRichTextString(header);
            cell.setCellValue(text);
            Cell++;
        }

        // 遍历集合数据，产生数据行
        Iterator<Map<String, Object>> it = dataMap.iterator();
        int index = 0;
        while (it.hasNext()) {
            index++;
            row = sheet.createRow(index);
            Map map = it.next();
            Cell = 0;
            for (String fieldName : col) {
                HSSFCell cell = row.createCell(Cell);
                cell.setCellStyle(style2);
                try {
                    Object value = map.get(fieldName);
                    if (value == null) value = "";
                    // 判断值的类型后进行强制类型转换
                    String textValue = null;
                    if (value instanceof Date) {
                        Date date = (Date) value;
                        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
                        textValue = sdf.format(date);
                    } else if (value instanceof byte[]) {
                        // 有图片时，设置行高为60px;
                        row.setHeightInPoints(60);
                        // 设置图片所在列宽度为80px,注意这里单位的一个换算
                        sheet.setColumnWidth(Cell, (short) (35.7 * 80));
                        // sheet.autoSizeColumn(i);
                        byte[] bsValue = (byte[]) value;
                        HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0,
                                1023, 255, (short) 6, index, (short) 6, index);
                        anchor.setAnchorType(2);
                        patriarch.createPicture(anchor, workbook.addPicture(
                                bsValue, HSSFWorkbook.PICTURE_TYPE_JPEG));
                    } else {
                        // 其它数据类型都当作字符串简单处理
                        textValue = value.toString();
                    }
                    // 如果不是图片数据，就利用正则表达式判断textValue是否全部由数字组成
                    if (textValue != null) {
                        Pattern p = Pattern.compile("^//d+(//.//d+)?$");
                        Matcher matcher = p.matcher(textValue);
                        if (matcher.matches()) {
                            // 是数字当作double处理
                            cell.setCellValue(Double.parseDouble(textValue));
                        } else {
                            HSSFRichTextString richString = new HSSFRichTextString(
                                    textValue);
                            cell.setCellValue(richString);
                        }
                    }
                    Cell++;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return workbook;
    }

    public static HSSFWorkbook exportExcel(String title, String[] headers, String[] col, List<Map<String, Object>> dataMap) {
        return exportExcel(title, headers, col, dataMap, "yyyy-MM-dd HH:mm:ss");
    }
}
