package com.example.demo.file;

import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NioFileUtil {

    public static void main(String[] args) throws Exception {
        System.out.println(read("1.txt"));
        transfer("1.txt","f:\\test\\a.txt");
    }


    public static void write(String message,String destFileName) throws Exception {
        if (StringUtils.isBlank(message)){
            return;
        }
        File file = new File(destFileName);
        FileOutputStream fos = new FileOutputStream(file);
        FileChannel channel = fos.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate((int)file.length());
        buffer.put(message.getBytes());
        buffer.flip();
        channel.write(buffer);
        fos.close();

    }


    public static String read(String srcFileName) throws Exception {
        File file = new File(srcFileName);
        FileInputStream inputStream = new FileInputStream(file);
        FileChannel channel = inputStream.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        int read = channel.read(byteBuffer);
        inputStream.close();
        return new String(byteBuffer.array(),0,read);
    }

    public static void transfer(String src,String dest) throws Exception{
        FileOutputStream fileOutputStream = new FileOutputStream(dest);
        FileInputStream inputStream = new FileInputStream(src);
        FileChannel destChannel = fileOutputStream.getChannel();
        FileChannel srcChannel = inputStream.getChannel();
        destChannel.transferFrom(srcChannel,0,srcChannel.size());
    }


}
