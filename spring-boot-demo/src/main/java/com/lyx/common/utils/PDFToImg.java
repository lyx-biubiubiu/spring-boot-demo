package com.lyx.common.utils;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfCopy;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zh
 * @data 2019/10/28 16:20
 * 说明：TODO
 */
public class PDFToImg {

//    public static void main(String[] args) throws IOException {
//        pdfToImagePath("E:\\inputPath\\test.pdf");
//
//    }

    /**
     * pdf拷贝到新文件，
     * @param pdfFile
     * @param newFile
     * @param from  从第几页考
     * @param end   考到第几页
     */
    public static void copyPdfFile(String pdfFile, String newFile, int from, int end) {
        Document document = null;
        PdfCopy copy = null;
        try {
            PdfReader reader = new PdfReader(pdfFile);
            int n = reader.getNumberOfPages();
            if (end == 0) {
                end = n;
            }
            ArrayList<String> savepaths = new ArrayList<String>();
            String staticpath = pdfFile.substring(0, pdfFile.lastIndexOf("\\") + 1);
            String savepath = staticpath + newFile;
            savepaths.add(savepath);
            document = new Document(reader.getPageSize(1));
            copy = new PdfCopy(document, new FileOutputStream(savepaths.get(0)));
            document.open();
            for (int j = from; j <= end; j++) {
                document.newPage();
                PdfImportedPage page = copy.getImportedPage(reader, j);
                copy.addPage(page);
            }
            document.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 将所有的pdf切割成一页
     */
    public static void cutOnePageFormPDF(String pdfFile, String toPath,String fileName){
        Document document = null;
        PdfCopy copy = null;
        try {
            PdfReader reader = new PdfReader(pdfFile);
            int n = reader.getNumberOfPages();
            for(int i=1; i <= n; i++){
                document = new Document(reader.getPageSize(1));
                copy = new PdfCopy(document, new FileOutputStream(toPath+fileName+"-"+i+".pdf"));
                document.open();
                document.newPage();
                PdfImportedPage page = copy.getImportedPage(reader, i);
                copy.addPage(page);
                document.close();
            }
            System.out.println(n);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 多个pdf合并
     */
    public static void sumPDFFile(String newfilePath, String... filePaths){
        int length = filePaths.length;
        Document document = null;
        PdfCopy copy = null;
        try {
            if(length > 0){
                document = new Document(new PdfReader(filePaths[0]).getPageSize(1));
                copy = new PdfCopy(document, new FileOutputStream(newfilePath));
                document.open();
                for(int i=0; i < length; i++){
                    PdfReader reader = new PdfReader(filePaths[i]);
                    for(int j=1; j <= reader.getNumberOfPages(); j++){
                        document.newPage();
                        PdfImportedPage page = copy.getImportedPage(reader, j);
                        copy.addPage(page);
                    }
                }
                document.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 将PDF按页数每页转换成一个jpg图片
     * @param filePath
     * @return
     */
    public static List<String> pdfToImagePath(String filePath){
        List<String> list = new ArrayList<>();
        String fileDirectory = filePath.substring(0,filePath.lastIndexOf("."));//获取去除后缀的文件路径

        String imagePath;
        File file = new File(filePath);
        try {
            File f = new File(fileDirectory);
            if(!f.exists()){
                f.mkdir();
            }
            PDDocument doc = PDDocument.load(file);
            PDFRenderer renderer = new PDFRenderer(doc);
            int pageCount = doc.getNumberOfPages();
            for(int i=0; i<pageCount; i++){
                // 方式1,第二个参数是设置缩放比(即像素)
                // BufferedImage image = renderer.renderImageWithDPI(i, 296);
                // 方式2,第二个参数是设置缩放比(即像素)
                BufferedImage image = renderer.renderImage(i, 1.8f);  //第二个参数越大生成图片分辨率越高，转换时间也就越长
                imagePath = fileDirectory + "/"+i + ".jpg";
                ImageIO.write(image, "PNG", new File(imagePath));
                list.add(imagePath);
            }
            doc.close();              //关闭文件,不然该pdf文件会一直被占用。
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static List<String> pdfToImagePath(byte[] inputStream,String filePath){
        List<String> list = new ArrayList<>();
        String fileDirectory = filePath.substring(0,filePath.lastIndexOf("."));//获取去除后缀的文件路径

        String imagePath;
        File file = new File(filePath);
        try {
            File f = new File(fileDirectory);
            if(!f.exists()){
                f.mkdir();
            }
            PDDocument doc = PDDocument.load(inputStream);
            PDFRenderer renderer = new PDFRenderer(doc);
            int pageCount = doc.getNumberOfPages();
            for(int i=0; i<pageCount; i++){
                // 方式1,第二个参数是设置缩放比(即像素)
                // BufferedImage image = renderer.renderImageWithDPI(i, 296);
                // 方式2,第二个参数是设置缩放比(即像素)
                BufferedImage image = renderer.renderImage(i, 1.8f);  //第二个参数越大生成图片分辨率越高，转换时间也就越长
                imagePath = fileDirectory + "/"+i + ".jpg";
                ImageIO.write(image, "PNG", new File(imagePath));
                list.add(imagePath);
            }
            doc.close();              //关闭文件,不然该pdf文件会一直被占用。
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * @Description pdf转成一张图片
     * @created 2019年4月19日 下午1:54:13
     * @param pdfFile
     * @param outpath
     */
    private static void pdf2multiImage(String pdfFile, String outpath) {
        try {
            InputStream is = new FileInputStream(pdfFile);
            PDDocument pdf = PDDocument.load(is);
            int actSize  = pdf.getNumberOfPages();
            List<BufferedImage> piclist = new ArrayList<BufferedImage>();
            for (int i = 0; i < actSize; i++) {
                BufferedImage  image = new PDFRenderer(pdf).renderImageWithDPI(i,130, org.apache.pdfbox.rendering.ImageType.RGB);
                piclist.add(image);
            }
            yPic(piclist, outpath);
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 将宽度相同的图片，竖向追加在一起 ##注意：宽度必须相同
     * @param piclist  文件流数组
     * @param outPath  输出路径
     */
    public static void yPic(List<BufferedImage> piclist, String outPath) {// 纵向处理图片
        if (piclist == null || piclist.size() <= 0) {
            System.out.println("图片数组为空!");
            return;
        }
        try {
            int height = 0, // 总高度
                    width = 0, // 总宽度
                    _height = 0, // 临时的高度 , 或保存偏移高度
                    __height = 0, // 临时的高度，主要保存每个高度
                    picNum = piclist.size();// 图片的数量
            int[] heightArray = new int[picNum]; // 保存每个文件的高度
            BufferedImage buffer = null; // 保存图片流
            List<int[]> imgRGB = new ArrayList<int[]>(); // 保存所有的图片的RGB
            int[] _imgRGB; // 保存一张图片中的RGB数据
            for (int i = 0; i < picNum; i++) {
                buffer = piclist.get(i);
                heightArray[i] = _height = buffer.getHeight();// 图片高度
                if (i == 0) {
                    width = buffer.getWidth();// 图片宽度
                }
                height += _height; // 获取总高度
                _imgRGB = new int[width * _height];// 从图片中读取RGB
                _imgRGB = buffer.getRGB(0, 0, width, _height, _imgRGB, 0, width);
                imgRGB.add(_imgRGB);
            }
            _height = 0; // 设置偏移高度为0
            // 生成新图片
            BufferedImage imageResult = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            for (int i = 0; i < picNum; i++) {
                __height = heightArray[i];
                if (i != 0) _height += __height; // 计算偏移高度
                imageResult.setRGB(0, _height, width, __height, imgRGB.get(i), 0, width); // 写入流中
            }
            File outFile = new File(outPath);
            ImageIO.write(imageResult, "jpg", outFile);// 写图片
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}