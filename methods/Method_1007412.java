public static boolean exists(Object vfsResource){
  if (!initialized) {
    init();
  }
  try {
    return (Boolean)invokeVfsMethod(VIRTUAL_FILE_METHOD_EXISTS,vfsResource);
  }
 catch (  IOException ex) {
    return false;
  }
}
