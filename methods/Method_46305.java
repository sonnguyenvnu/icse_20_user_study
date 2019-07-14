@Override public void initChannel(SocketChannel ch){
  if (sslCtx != null) {
    configureSSL(ch);
  }
 else {
    configureClearText(ch);
  }
}
