package com.nio;

import cn.hutool.core.io.IoUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Files;
import java.nio.file.Paths;

public class NioFileChannel {
    public static String urlToBase64Sting(String url) {
        ReadableByteChannel readableByteChannel = null;
        FileOutputStream fileOutputStream = null;
        File file;
        try {
            readableByteChannel = Channels.newChannel(new URL(url).openStream());
            file = File.createTempFile("img_download", null);
            fileOutputStream = new FileOutputStream(file.getAbsoluteFile());
            fileOutputStream.getChannel()
                    .transferFrom(readableByteChannel, 0, Long.MAX_VALUE);
            readableByteChannel.close();
            fileOutputStream.close();
            return java.util.Base64.getEncoder().encodeToString(Files.readAllBytes(Paths.get(file.getAbsolutePath())));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IoUtil.close(readableByteChannel);
            IoUtil.close(fileOutputStream);
        }
        return url;
    }
}
