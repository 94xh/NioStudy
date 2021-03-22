package com.io;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;

public class BlockingFile {
    public static String urlToBase64Sting(String urlOrPath) {

        byte[] imgBytes = new byte[1024 * 1024];

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        BufferedInputStream reader = null;
        InputStream in = null;
        URLConnection conn = null;
        java.io.File temFile = null;

        try {
            if (!urlOrPath.startsWith("http:")) {
                java.io.File imgFile = new java.io.File(urlOrPath);
                if (!imgFile.isFile() || !imgFile.exists() || !imgFile.canRead()) {
                    return "";
                }
                in = new FileInputStream(imgFile);
            } else {
                URL imgUrl = new URL(urlOrPath);
                conn = imgUrl.openConnection();
                temFile = new java.io.File(new Date().getTime() + ".jpg");
                FileOutputStream tem = new FileOutputStream(temFile);
                BufferedImage image = ImageIO.read(conn.getInputStream());
                ImageIO.write(image, "jpg", tem);
                in = new FileInputStream(temFile);
            }
            reader = new BufferedInputStream(in);
            byte[] buffer = new byte[1024];
            while (reader.read(buffer) != -1) {
                out.write(buffer);
            }
            imgBytes = out.toByteArray();

        } catch (Exception e) {
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (temFile != null) {
                temFile.delete();
            }
        }
        return java.util.Base64.getEncoder().encodeToString(imgBytes);
    }
}
