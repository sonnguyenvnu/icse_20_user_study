public static void register(Class delegate,Class implementationClass){
  instance().implementations.put(delegate,implementationClass);
}
