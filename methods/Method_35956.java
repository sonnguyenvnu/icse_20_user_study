private static boolean isClassExist(String type){
  try {
    ClassLoader contextCL=Thread.currentThread().getContextClassLoader();
    ClassLoader loader=contextCL == null ? JettyUtils.class.getClassLoader() : contextCL;
    Class.forName(type,false,loader);
    return true;
  }
 catch (  Exception e) {
    return false;
  }
}
