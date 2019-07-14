private static byte[] processStatusCodeReply(final RedisInputStream is){
  return is.readLineBytes();
}
