public static RatpackServerProxy capture(ClassLoader classLoader,final String mainClassName,final String[] appArgs){
  Class<?> mainClass;
  try {
    mainClass=classLoader.loadClass(mainClassName);
  }
 catch (  ClassNotFoundException e) {
    throw new RuntimeException("Did not find specified main class: " + mainClassName,e);
  }
  Method main;
  try {
    main=mainClass.getMethod("main",String[].class);
  }
 catch (  NoSuchMethodException e) {
    throw new RuntimeException("Did not find main(String...) method on main class: " + mainClassName,e);
  }
  if (!Modifier.isStatic(main.getModifiers())) {
    throw new RuntimeException("main(String...) is not static on class: " + mainClassName);
  }
  Class<?> capturer;
  try {
    capturer=classLoader.loadClass(CAPTURER_CLASS_NAME);
  }
 catch (  ClassNotFoundException e) {
    throw new RuntimeException("could not load " + CAPTURER_CLASS_NAME,e);
  }
  Class<?> blockType;
  try {
    blockType=classLoader.loadClass(BLOCK_CLASS_NAME);
  }
 catch (  ClassNotFoundException e) {
    throw new RuntimeException("could not load " + BLOCK_CLASS_NAME,e);
  }
  Method captureMethod;
  try {
    captureMethod=capturer.getMethod("capture",blockType);
  }
 catch (  NoSuchMethodException e) {
    throw new RuntimeException("Could not find capture() on ServerCapturer");
  }
  final Method finalMain=main;
  Object block=Proxy.newProxyInstance(classLoader,new Class<?>[]{blockType},new InvocationHandler(){
    @Override public Object invoke(    Object proxy,    Method method,    Object[] args) throws Throwable {
      try {
        finalMain.invoke(null,new Object[]{appArgs});
      }
 catch (      Exception e) {
        throw new RuntimeException("failed to invoke main(String...) on class: " + mainClassName,e);
      }
      return null;
    }
  }
);
  Object server;
  try {
    server=captureMethod.invoke(null,block);
  }
 catch (  Exception e) {
    throw new RuntimeException("Failed to invoke get() on ServerCapturer");
  }
  if (server == null) {
    throw new RuntimeException("main(String...) of " + mainClassName + " did not start a Ratpack server");
  }
  if (!server.getClass().getName().equals(SERVER_CLASS_NAME)) {
    throw new RuntimeException("Captured " + server.getClass().getName() + ", not " + SERVER_CLASS_NAME);
  }
  return new RatpackServerProxy(server);
}
