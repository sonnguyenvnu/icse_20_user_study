@Override public void run(){
  try {
    Connection connection=DriverManager.getConnection(url,user,password);
synchronized (this) {
      availableConnections.add(new ConnectionData(connection));
      connectionPending=false;
      notifyAll();
    }
  }
 catch (  Exception ex) {
  }
}
