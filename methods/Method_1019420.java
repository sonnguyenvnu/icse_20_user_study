public static Socket wrap(Socket socket,SocketMonitor... monitors){
  return new MonitorSocketWrapper(socket,monitors);
}
