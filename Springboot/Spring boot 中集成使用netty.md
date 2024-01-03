# Spring boot 中集成使用netty

### netty的基本类创建在之前篇章以及介绍过

### 在之前已创建好的netty类之上，

### 使用后端架构来使用netty

#### 1.编写service层

```
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Service
public class NettyService {

@Autowired
private EchoServer echoServer; // 注入您的Netty服务器

@PostConstruct
public void startNettyServer() throws Exception {
    echoServer.run(); // 启动Netty服务器
}

@PreDestroy
public void stopNettyServer() {
    echoServer.stop(); // 关闭Netty服务器
}
}
```

#### 2.编写controller层

```
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/netty")
public class NettyController {

@Autowired
private NettyService nettyService;

@RequestMapping("/start")
public String startNetty() {
    try {
        nettyService.startNettyServer();
        return "Netty server started.";
    } catch (Exception e) {
        e.printStackTrace();
        return "Error starting Netty server: " + e.getMessage();
    }
}

@RequestMapping("/stop")
public String stopNetty() {
    try {
        nettyService.stopNettyServer();
        return "Netty server stopped.";
    } catch (Exception e) {
        e.printStackTrace();
        return "Error stopping Netty server: " + e.getMessage();
    }
}
}
```

## 总结：至此，即可通过url - >base/netty/start(/stop) 来启用/关闭netty服务