//package com.lyx.common.utils;
//import com.itextpdf.text.Document;
//import com.itextpdf.text.DocumentException;
//import com.itextpdf.text.pdf.PdfCopy;
//import com.itextpdf.text.pdf.PdfImportedPage;
//import com.itextpdf.text.pdf.PdfReader;
//
//import java.io.FileOutputStream;
//
//import java.io.IOException;
//
//import java.util.ArrayList;
//
//
//public class PDFUtils2 {
//
//    private static final int N = 5;
//
//
//    public static void main(String[] args) {
//
////        String[] files = {"C:\\a.pdf", "C:\\b.pdf"};
////
////        String savepath = "E:\\outputPath";
////
////        mergePdfFiles(files, savepath);
//
//        partitionPdfFile("E:\\inputPath\\test.pdf");
//
//    }
//
//
//    public static void mergePdfFiles(String[] files, String savepath) {
//
//        try {
//
//            Document document = new Document(new PdfReader(files[0]).getPageSize(1));
//
//            PdfCopy copy = new PdfCopy(document, new FileOutputStream(savepath));
//
//
//            document.open();
//
//
//            for (int i = 0; i < files.length; i++) {
//
//                PdfReader reader = new PdfReader(files[i]);
//
//
//                int n = reader.getNumberOfPages();
//
//
//                for (int j = 1; j <= n; j++) {
//
//                    document.newPage();
//
//                    PdfImportedPage page = copy.getImportedPage(reader, j);
//
//                    copy.addPage(page);
//
//                }
//
//            }
//
//
//            document.close();
//
//
//        } catch (IOException e) {
//
//            e.printStackTrace();
//
//        } catch (DocumentException e) {
//
//            e.printStackTrace();
//
//        }
//
//    }
//
//
//    public static void partitionPdfFile(String filepath) {
//
//        Document document = null;
//        PdfCopy copy = null;
//        try {
//            PdfReader reader = new PdfReader(filepath);
//            int n = reader.getNumberOfPages();
//            if (n < (n/N)) {
//                System.out.println("The document does not have " + N + " pages to partition !");
//                return;
//            }
//
//            int size =N;
//
//            String staticpath = filepath.substring(0, filepath.lastIndexOf("\\") + 1);
//
//            String savepath = null;
//
//            ArrayList<String> savepaths = new ArrayList<String>();
//
//            for (int i = 1; i <= (n/N); i++) {
//
//                if (i < 10) {
//
//                    savepath = filepath.substring(filepath.lastIndexOf("\\") + 1, filepath.length() - 4);
//
//                    savepath = staticpath + savepath + "0" + i + ".pdf";
//
//                    savepaths.add(savepath);
//
//                } else {
//
//                    savepath = filepath.substring(filepath.lastIndexOf("\\") + 1, filepath.length() - 4);
//
//                    savepath = staticpath + savepath + i + ".pdf";
//
//                    savepaths.add(savepath);
//
//                }
//
//            }
//
//
//            for (int i = 0; i < (n/N) - 1; i++) {
//
//                document = new Document(reader.getPageSize(1));
//
//                copy = new PdfCopy(document, new FileOutputStream(savepaths.get(i)));
//
//                document.open();
//
//                for (int j = size * i + 1; j <= size * (i + 1); j++) {
//
//                    document.newPage();
//
//                    PdfImportedPage page = copy.getImportedPage(reader, j);
//
//                    copy.addPage(page);
//
//                }
//
//                document.close();
//
//            }
//
//
//            document = new Document(reader.getPageSize(1));
//
//            copy = new PdfCopy(document, new FileOutputStream(savepaths.get((n/N) - 1)));
//
//            document.open();
//
//            for (int j = size * ((n/N) - 1) + 1; j <= n; j++) {
//
//                document.newPage();
//
//                PdfImportedPage page = copy.getImportedPage(reader, j);
//
//                copy.addPage(page);
//
//            }
//
//            document.close();
//
//
//        } catch (IOException e) {
//
//            e.printStackTrace();
//
//        } catch (DocumentException e) {
//
//            e.printStackTrace();
//
//        }
//
//    }
//
//}
