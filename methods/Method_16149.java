@Override protected Object readInternal(Class<?> clazz,HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
  return read(clazz,clazz,inputMessage);
}
