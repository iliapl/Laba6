package Server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;

public class Connector {
    /*
    Используется для создания подключения
     */
    private ServerSocketChannel serverSocketChannel;
    public Connector(String name, int port) throws IOException {
        serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.socket().bind(new InetSocketAddress(name, port));
    }
    /*
    Открываем сетевой канал с заданым именем и портом
     */
    public void start(){
        while(){

        }
    }
    /*
    запускаем канал для чтения запросов на подключение
     */
}
