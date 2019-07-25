public static void register(Class delegate,Object implementationInstance){
  instance().implementations.put(delegate,implementationInstance);
}
