public void psubscribe(byte[]... patterns){
  client.psubscribe(patterns);
  client.flush();
}
