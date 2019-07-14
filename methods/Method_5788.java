private byte[] getInitializationVector(long nonce,long counter){
  return ByteBuffer.allocate(16).putLong(nonce).putLong(counter).array();
}
