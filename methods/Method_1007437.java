public byte[] generate() throws Exception {
  Class<?> proxyGeneratorClass=null;
  try {
    proxyGeneratorClass=getClass().getClassLoader().loadClass("java.lang.reflect.ProxyGenerator");
  }
 catch (  ClassNotFoundException e) {
    try {
      proxyGeneratorClass=getClass().getClassLoader().loadClass("sun.misc.ProxyGenerator");
    }
 catch (    ClassNotFoundException ex) {
      LOGGER.error("Unable to loadClass ProxyGenerator!");
      return null;
    }
  }
  return (byte[])ReflectionHelper.invoke(null,proxyGeneratorClass,"generateProxyClass",new Class[]{String.class,Class[].class},classBeingRedefined.getName(),classBeingRedefined.getInterfaces());
}
