public static boolean isOverridenMethod(Class<?> clazz,Method method,boolean checkThisClass){
  try {
    if (checkThisClass) {
      clazz.getDeclaredMethod(method.getName(),method.getParameterTypes());
      return true;
    }
  }
 catch (  NoSuchMethodException ignored) {
  }
  if (clazz.getSuperclass() != null) {
    if (isOverridenMethod(clazz.getSuperclass(),method,true)) {
      return true;
    }
  }
  for (  Class<?> anInterface : clazz.getInterfaces()) {
    if (isOverridenMethod(anInterface,method,true)) {
      return true;
    }
  }
  return false;
}
