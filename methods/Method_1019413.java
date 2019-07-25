@Override public Socket accept() throws SocksException, IOException {
  CommandReplyMessage messge=socksCmdSender.checkServerReply(proxySocket.getInputStream());
  logger.debug("accept a connection from:{}",messge.getSocketAddress());
  return this.proxySocket;
}
