package org.jee.rpc;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * �??述:Rpc本地�?务代�?�类
 * 1. 将本地接�?�调用转化为JDK的动�?调用,在动�?调用中实现接�?�的远程调用
 * 2. 创建Socket客户端,根�?�制定地�?�连接远程�?务�??供者
 * 3. 将远程�?务调用所需的接�?�类,方法�??,�?�数列表等编�?�?��?��?给�?务�??供者
 * 4. �?�步阻塞等待�?务端返回应答,获�?�应答�?�返回
 * Created by bysocket on 16/2/29.
 */
public class RpcImporter<S> {
    public S importer(final Class<?> serviceClass, final InetSocketAddress address) {
        // JDK动�?代�?�,实现接�?�的远程调用
        return (S) Proxy.newProxyInstance(serviceClass.getClassLoader(),
                new Class<?>[]{serviceClass.getInterfaces()[0]},
                new InvocationHandler() {

                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        Socket socket = null;
                        ObjectOutputStream output = null;
                        ObjectInputStream  input  = null;

                        try {
                            // 连接远程�?务�??供者
                            socket = new Socket();
                            socket.connect(address);

                            // 对象输出�?
                            output = new ObjectOutputStream(socket.getOutputStream());
                            output.writeUTF(serviceClass.getName());
                            output.writeUTF(method.getName());
                            output.writeObject(method.getParameterTypes());
                            output.writeObject(args);

                            input = new ObjectInputStream(socket.getInputStream());
                            return input.readObject();
                        } finally {
                            if (socket != null) {
                                socket.close();
                            }
                            if (output != null) {
                                output.close();
                            }
                            if (input != null) {
                                input.close();
                            }
                        }
                    }
                });
    }
}
