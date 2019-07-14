private Throwable createException(String message,Throwable cause,Class<?> exClass) throws Exception {
  Constructor<?> defaultConstructor=null;
  Constructor<?> messageConstructor=null;
  Constructor<?> causeConstructor=null;
  for (  Constructor<?> constructor : exClass.getConstructors()) {
    Class<?>[] types=constructor.getParameterTypes();
    if (types.length == 0) {
      defaultConstructor=constructor;
      continue;
    }
    if (types.length == 1 && types[0] == String.class) {
      messageConstructor=constructor;
      continue;
    }
    if (types.length == 2 && types[0] == String.class && types[1] == Throwable.class) {
      causeConstructor=constructor;
      continue;
    }
  }
  if (causeConstructor != null) {
    return (Throwable)causeConstructor.newInstance(message,cause);
  }
  if (messageConstructor != null) {
    return (Throwable)messageConstructor.newInstance(message);
  }
  if (defaultConstructor != null) {
    return (Throwable)defaultConstructor.newInstance();
  }
  return null;
}
