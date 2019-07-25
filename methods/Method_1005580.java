public static Class clazz(){
  try {
    return RefInvoker.forName(ClassName);
  }
 catch (  ClassNotFoundException e) {
    LogUtil.printException("HackActivityThread.clazz",e);
  }
  return null;
}
