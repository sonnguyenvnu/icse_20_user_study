@Override public String deserialize(byte[] bytes){
  return (bytes == null ? null : new String(bytes,charset));
}
