//package com.lyx.common.utils;
//
//import com.itextpdf.text.Document;
//import com.itextpdf.text.DocumentException;
//import com.itextpdf.text.pdf.PdfCopy;
//import com.itextpdf.text.pdf.PdfImportedPage;
//import com.itextpdf.text.pdf.PdfReader;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.IOException;
//
///**
// * @author 常乐
// * @create 2020-05-2020/5/12 17:24
// */
//public class PDFUtils {
//
//    private static String path = "E:\\inputPath";
//
//    /**
//     * @author Reverse_XML
//     * 把PDF 按页(逐页、单页)拆分(一页一页拆分，一页为一个新PDF文件)
//     * @param path 源PDF路径
//     * @param fileName 源PDF文件名
//     * @param outputPath 拆分后输出的PDF路径
//     */
//    public static void splitPDFOneByOne(String path, String fileName, String outputPath) {
//        String sep = File.separator;
//        PdfReader reader = null;
//        int numberOfPages = 0;
//        try {
//            reader = new PdfReader(path + sep + fileName);
//            numberOfPages = reader.getNumberOfPages();
//            for (int i = 1; i <= numberOfPages; i++) {
//                Document document = null;
//                PdfCopy copy = null;
//                try {
//                    document = new Document(reader.getPageSize(1));
//                    String savePath = outputPath + sep +
//                            fileName.substring(0, fileName.lastIndexOf(".")) + "_" + i + ".pdf";
//                    copy = new PdfCopy(document, new FileOutputStream(savePath));
//                    document.open();
//                    document.newPage();
//                    PdfImportedPage page = copy.getImportedPage(reader, i);
//                    copy.addPage(page);
//                } finally {
//                    if (document != null)
//                        document.close();
//                    if (copy != null)
//                        copy.close();
//                }
//            }
//        } catch (IOException e) {
//            System.out.println(e.getMessage());
//        } catch (DocumentException e) {
//            System.out.println(e.getMessage());
//        } finally {
//            if (reader != null)
//                reader.close();
//        }
//    }
//
//}
