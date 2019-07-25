@Override public T deserialize(byte[] bytes) throws SerializationException {
  if (bytes == null || bytes.length <= 0) {
    return null;
  }
  String str=new String(bytes,DEFAULT_CHARSET);
  return (T)JSON.parseObject(str,clazz);
}
