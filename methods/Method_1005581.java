public static Class clazz(){
  try {
    return RefInvoker.forName(ClassName);
  }
 catch (  ClassNotFoundException e) {
    LogUtil.printException("HackActivityThreadProviderClientRecord.clazz",e);
  }
  return null;
}
