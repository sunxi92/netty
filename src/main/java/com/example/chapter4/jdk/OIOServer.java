package com.example.chapter4.jdk;

import io.netty.util.CharsetUtil;
import org.springframework.boot.autoconfigure.couchbase.CouchbaseProperties;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;

public class OIOServer {
    public void serve(int port) throws Exception{
        final ServerSocket serverSocket = new ServerSocket(port);

        try{
            for(;;){
                final Socket clientSocket = serverSocket.accept();
                System.out.println("accept connection from " + clientSocket);

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        OutputStream outputStream;
                        try{
                            outputStream = clientSocket.getOutputStream();
                            outputStream.write("hello!\r\n".getBytes(Charset.forName("UTF-8")));
                            outputStream.flush();
                            clientSocket.close();
                        }catch (IOException e){
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        }catch (IOException exception){
            exception.printStackTrace();
        }
    }



}
