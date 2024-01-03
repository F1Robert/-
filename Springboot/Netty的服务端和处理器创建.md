# Netty的服务端和处理器创建

#### Netty是一个用于构建高性能网络应用程序的框架，而Spring Boot是一个用于构建Java应用程序的框架。您可以将Netty与Spring Boot联合使用，以构建具有高性能网络通信能力的应用程序，例如实时聊天应用、在线游戏服务器、消息队列处理等。

#### 以下是将Netty与Spring Boot联合使用的一般步骤：

#### 1.添加依赖

```
<dependencies>
    <dependency>
        <groupId>io.netty</groupId>
        <artifactId>netty-all</artifactId>
        <version>4.1.66.Final</version> <!-- 使用最新版本 -->
    </dependency>
</dependencies>
```

#### 2.**编写Netty代码**：在Spring Boot应用程序中，您可以编写Netty相关的代码来处理网络通信。这可能包括创建Netty服务器或客户端，定义处理程序（handlers）来处理收到的消息，设置通信协议和端口等。

#### 3.**集成到Spring Boot**：将Netty代码集成到Spring Boot应用程序中，可以使用Spring的依赖注入和生命周期管理等特性。您可以创建Spring的Service组件来管理Netty服务器或客户端，并将它们纳入Spring应用程序的上下文。

#### 4.**管理Netty生命周期**：在Spring Boot中，您可以使用`@PostConstruct`和`@PreDestroy`等注解来管理Netty组件的初始化和关闭。确保在应用程序启动时启动Netty服务器，以及在应用程序关闭时优雅地关闭Netty服务器，以释放资源。

#### 5.**处理业务逻辑**：根据您的应用程序需求，编写业务逻辑，以处理Netty服务器或客户端接收到的消息。这可以是处理聊天消息、游戏逻辑、消息队列处理等。

#### 6.**配置Spring Boot属性**：根据需要，您可以将Netty的配置属性外部化，以便在不同环境中进行配置更改，例如端口号、协议、线程池大小等。

#### 7.**测试和部署**：进行测试以确保Netty和Spring Boot的联合使用正常工作。一旦测试通过，您可以将应用程序部署到生产环境中。

#### 请注意，具体的实现细节会根据您的应用程序需求而有所不同。关键是理解如何将Netty的网络通信能力集成到Spring Boot应用程序中，并根据需要进行配置和管理。这样，您可以利用Spring Boot的便捷性和Netty的高性能来构建强大的应用程序。



#### 使用netty举例 

#### EchoServer 

#### 创建netty服务端

```
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class EchoServer {
    private final int port;

public EchoServer(int port) {
    this.port = port;
}

public static void main(String[] args) throws Exception {
    int port = 8080;
    new EchoServer(port).run();
}

public void run() throws Exception {
    EventLoopGroup bossGroup = new NioEventLoopGroup(); // 用于接收客户端连接
    EventLoopGroup workerGroup = new NioEventLoopGroup(); // 用于处理客户端请求

​    try {
​        ServerBootstrap bootstrap = new ServerBootstrap();
​        bootstrap.group(bossGroup, workerGroup)
​                 .channel(NioServerSocketChannel.class)
​                 .childHandler(new ChannelInitializer<SocketChannel>() {
​                     @Override
​                     public void initChannel(SocketChannel ch) throws Exception {
​                         ChannelPipeline pipeline = ch.pipeline();
​                         pipeline.addLast(new EchoServerHandler());
​                     }
​                 });

​        ChannelFuture future = bootstrap.bind(port).sync();
​        future.channel().closeFuture().sync();
​    } finally {
​        bossGroup.shutdownGracefully();
​        workerGroup.shutdownGracefully();
​    }
}

}
```

#### 创建netty EchoServerHandler消息处理器

#### 它负责处理接收到的消息并将其回显给客户端：

```
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class EchoServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        ByteBuf in = (ByteBuf) msg;
        ctx.write(in); // 回显收到的消息
    }

@Override
public void channelReadComplete(ChannelHandlerContext ctx) {
    ctx.flush(); // 刷新消息，将回显的消息发送给客户端
}

@Override
public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
    cause.printStackTrace();
    ctx.close(); // 发生异常时关闭连接
}
}
```



#### 当前端使用websockt来连接后端时，netty的数据处理器应该创建如下

```
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;

public class WebSocketHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        if (msg instanceof WebSocketFrame) {
            WebSocketFrame frame = (WebSocketFrame) msg;
            if (frame instanceof TextWebSocketFrame) {
                String message = ((TextWebSocketFrame) frame).text();
                // 处理收到的消息，执行业务逻辑
                // 发送消息给客户端
                ctx.writeAndFlush(new TextWebSocketFrame("服务器回复: " + message));
            }
        }
    }

@Override
public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
    cause.printStackTrace();
    ctx.close();
}

}
```



#### 这个简单的示例演示了如何使用Netty创建一个Echo服务器，它接收来自客户端的消息并将其回显给客户端。当客户端连接到服务器时，服务器将消息原样返回给客户端。`EchoServerHandler`类实现了`channelRead`方法，用于处理接收到的消息，并在`channelReadComplete`方法中刷新消息以发送回客户端。在`exceptionCaught`方法中处理异常情况。



