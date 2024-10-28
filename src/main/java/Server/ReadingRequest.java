package Server;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class ReadingRequest {
    private ServerSocketChannel serverSocketChannel;
    private Selector selector;
    private SelectionKey registeredKey;
    private ReadCommand readCommand;
    private AnswerToClient answerToClient;
    public ReadingRequest(ServerSocketChannel serverSocketChannel) throws IOException {
        this.serverSocketChannel = serverSocketChannel;
        selector = Selector.open();
        registeredKey = serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        readCommand = new ReadCommand();
        answerToClient = new AnswerToClient();
    }
    public void checkChannel() throws IOException {
        while (true){
            selector.select(key -> {
                if (key.isAcceptable()) {
                    try {
                        //Принятие подключения серверным сокетом
                        ServerSocketChannel server = (ServerSocketChannel) key.channel();
                        SocketChannel client = server.accept();
                        client.configureBlocking(false);
                        //Регистрируем принятое подключение в селекторе с интересующим типом операции - чтение
                        client.register(selector, SelectionKey.OP_READ);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }

                if (key.isReadable()) {
                    try {
                        //Тут происходит обработка принятых подключений
                        SocketChannel client = (SocketChannel) key.channel();
                        ByteBuffer requestBuffer = ByteBuffer.allocate(100);
                        int r = client.read(requestBuffer);
                        if (r == -1) {
                            client.close();
                        } else {
                            //В этом блоке происходит обработка запроса
                           readCommand.Read();
                           answerToClient.answer();
                        }
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        }
    }
    /*
    С помощью Сервер сокета проверяем готовность канала к соединению
    Соединяем
    !!!!!!!На обработку запроса пока стоит заглушка!!!!!!!!!
    возможно, вероятнее всего после каждого запроса клиент будет отключаться от сервера
     */
}
