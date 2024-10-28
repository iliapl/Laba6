package Server;

import lombok.Getter;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

//на обработке запроса стоит заглушка
public class Connector {
    /*
    Используется для создания подключения
     */
    @Getter
    private ServerSocketChannel serverSocketChannel;
    private Selector selector;
    private SelectionKey registeredKey;
    public Connector(String name, int port) throws IOException {
        serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.socket().bind(new InetSocketAddress(name, port));
        serverSocketChannel.configureBlocking(false);//задаём параметр блокировки как неблокирующий
        /*
        Далее открываем селектор и записывем в него ранее открытый поток
        Нужен для "приятного" подключения
        Селектор детектит запрос на подключение
        Пробегается по доступным каналам и спрашивает
        А вы готовы к подключению?
        Далее, если есть каналы, ожидающие обработки,
        мы вытаскиваем множество готовых SelectedKey и обрабатываем их.
         */
    }
    /*
    Открываем сетевой канал с заданым именем и портом
     */
    public void start() throws IOException {
            serverSocketChannel.accept();
    }
    /*
    запускаем канал для чтения запросов на подключение
     */

    /*
    С помощью Сервер сокета проверяем готовность канала к соединению
    Соединяем
    !!!!!!!На обработку запроса пока стоит заглушка!!!!!!!!!
     */
    /*
    public void close() throws IOException {
        serverSocketChannel.close();
    }
    Вырубаем наш канал и сервер тоже))))))))
    не нужен
     */
}
