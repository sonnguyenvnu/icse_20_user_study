public URL toStandardJavaUrl(URL url){
  try {
    Class<?> vfsClass=Class.forName("org.jboss.virtual.VFS");
    Class<?> vfsUtilsClass=Class.forName("org.jboss.virtual.VFSUtils");
    Class<?> virtualFileClass=Class.forName("org.jboss.virtual.VirtualFile");
    Method getRootMethod=vfsClass.getMethod("getRoot",URL.class);
    Method getRealURLMethod=vfsUtilsClass.getMethod("getRealURL",virtualFileClass);
    Object root=getRootMethod.invoke(null,url);
    return (URL)getRealURLMethod.invoke(null,root);
  }
 catch (  Exception e) {
    throw new FlywayException("JBoss VFS v2 call failed",e);
  }
}
