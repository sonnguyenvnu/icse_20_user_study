@Override public Object deserialize(byte[] bytes){
  if (bytes == null || bytes.length == 0) {
    return null;
  }
  try {
    return fst.asObject(bytes);
  }
 catch (  Throwable ex) {
    LOG.error(ex.toString(),ex);
  }
  return null;
}
