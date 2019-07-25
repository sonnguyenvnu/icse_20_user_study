@Override public void request(int numMessages){
  if (ctx.eventLoop().inEventLoop()) {
    messageReader.request(numMessages);
  }
 else {
    ctx.eventLoop().submit(() -> messageReader.request(numMessages));
  }
}
