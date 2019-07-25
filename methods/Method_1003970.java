public static <T>ThriftFactory<T> create(Class<T> serviceInterface){
  return new ThriftFactory<T>(serviceInterface);
}
