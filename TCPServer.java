import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {
    public static void main(String[] args) throws IOException {
        //1.创建一个ServerSocket对象
        ServerSocket server = new ServerSocket(8888);
        //2.用对象获取客户端发来的socket对象
        Socket socket = server.accept();
        //3.用socket对象中的getInputStream方法获取一个InputStram对象
        InputStream inputStream = socket.getInputStream();
        //4.用对象中的read方法读取数据
        byte[] bytes = new byte[1024];
        int is = inputStream.read(bytes);
        System.out.println(new String(bytes));
        //5.获取字节输出流
        OutputStream outputStream = socket.getOutputStream();
        //6.用输出对象中的write方法往客户端写数据
        outputStream.write("收到谢谢".getBytes());
        //7.释放资源
        socket.close();
        server.close();
    }
}
