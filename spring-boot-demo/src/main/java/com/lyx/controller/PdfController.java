package com.lyx.controller;



import com.lyx.common.utils.PDFToImg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@RestController("pdf")
public class PdfController {
    Logger logger = LoggerFactory.getLogger(PdfController.class);

    @PostMapping("ImportPdf")
    public String ImportPdf(MultipartFile file){
        logger.info("图片开始上传");


        try {
            byte[] bytes = file.getBytes();
            List<String> list = PDFToImg.pdfToImagePath(bytes, "E:\\inputPath\\test21.pdf");
            for (String s : list) {
                System.out.print(" "+s+" " );
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    return null;
    }





}
