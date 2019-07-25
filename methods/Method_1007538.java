public byte[] transform(ClassLoader loader,String name,Class klass,ProtectionDomain protectionDomain,byte[] bytes) throws IllegalClassFormatException {
  String memory="com.sun.tools.javac.launcher.Main$MemoryClassLoader";
  if (loader != null && memory.equals(loader.getClass().getName())) {
    String cname=WeavingClassLoader.makeResourceName(name);
    map.put(cname,bytes);
  }
  return bytes;
}
