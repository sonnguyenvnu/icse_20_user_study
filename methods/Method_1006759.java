public S importer(final Class<?> serviceClass,final InetSocketAddress address){
  return (S)Proxy.newProxyInstance(serviceClass.getClassLoader(),new Class<?>[]{serviceClass.getInterfaces()[0]},new InvocationHandler(){
    @Override public Object invoke(    Object proxy,    Method method,    Object[] args) throws Throwable {
      Socket socket=null;
      ObjectOutputStream output=null;
      ObjectInputStream input=null;
      try {
        socket=new Socket();
        socket.connect(address);
        output=new ObjectOutputStream(socket.getOutputStream());
        output.writeUTF(serviceClass.getName());
        output.writeUTF(method.getName());
        output.writeObject(method.getParameterTypes());
        output.writeObject(args);
        input=new ObjectInputStream(socket.getInputStream());
        return input.readObject();
      }
  finally {
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
  }
);
}
