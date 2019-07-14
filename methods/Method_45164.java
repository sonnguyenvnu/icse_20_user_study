public static ActualSocketServer createLogServer(final int port){
  return new ActualSocketServer(port,new Slf4jMonitor(new SocketRequestDumper(),new SocketResponseDumper()));
}
