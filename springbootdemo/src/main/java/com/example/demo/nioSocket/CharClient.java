package com.example.demo.nioSocket;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class CharClient {
    public static void main(String[] args) throws Exception{
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(true);
        if(!socketChannel.connect(new InetSocketAddress("127.0.0.1", 9999))){
            while (!socketChannel.finishConnect()) {
                System.out.println("未连接成功,可以做其他的事");
            }
        }
        String msg = "hello";
        socketChannel.write(ByteBuffer.wrap(msg.getBytes()));
        System.in.read();
    }
}
