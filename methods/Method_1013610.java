@SuppressWarnings({"unchecked","rawtypes"}) public long pid(){
  try {
    final ClassLoader classLoader=getClass().getClassLoader();
    final Class aClass=classLoader.loadClass("java.lang.ProcessHandle");
    final Object o=aClass.getMethod("current").invoke(null,(Object[])null);
    return (long)aClass.getMethod("pid").invoke(o,(Object[])null);
  }
 catch (  Exception e) {
    return -1;
  }
}
