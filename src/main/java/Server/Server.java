package Server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.channels.ServerSocketChannel;

public class Server {
    private Connector connector;
   public static void main(String[] arg) throws IOException {
       Connector connector = new Connector("A", 9999);
       ReadingRequest readingRequest = new ReadingRequest(connector.getServerSocketChannel());
       while(true) {
           connector.start();
           readingRequest.checkChannel();

       }
   }
}
