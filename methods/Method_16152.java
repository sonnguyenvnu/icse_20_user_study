public Object readByString(Class<?> clazz,String jsonStr){
  return readByBytes(clazz,jsonStr.getBytes());
}
