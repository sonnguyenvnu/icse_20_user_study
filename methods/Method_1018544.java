default byte[] serialize(){
  return toCbor().toByteArray();
}
