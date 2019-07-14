public static Method findMethodByName(@NonNull String name,@NonNull Object subscriber){
  if (TextUtils.isEmpty(name)) {
    name="execute";
  }
  Map<String,Method> methodPair=methodCache.get(subscriber);
  if (methodPair == null) {
    methodPair=new HashMap<>();
    methodCache.put(subscriber,methodPair);
  }
  Method method=methodPair.get(name);
  if (method == null) {
    Class<?> subscriberClass=subscriber.getClass();
    try {
      Class<?> clazz=subscriberClass;
      while (clazz != null && !clazz.equals(Object.class)) {
        method=subscriberClass.getMethod(name,Event.class);
        if (method != null) {
          methodPair.put(name,method);
          break;
        }
      }
    }
 catch (    NoSuchMethodException e) {
      e.printStackTrace();
    }
  }
  return method;
}
