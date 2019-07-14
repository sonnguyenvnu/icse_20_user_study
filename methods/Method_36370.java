@SuppressWarnings("unchecked") private <T>T getMethodValueByReflect(Method method,Object obj,Object... args){
  try {
    return (T)method.invoke(obj,args);
  }
 catch (  Throwable e) {
    throw new RuntimeException(e);
  }
}
