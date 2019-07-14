public void punsubscribe(byte[]... patterns){
  client.punsubscribe(patterns);
  client.flush();
}
