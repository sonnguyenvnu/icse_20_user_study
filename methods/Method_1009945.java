@Nullable private static RouterInterceptor create(@NonNull Class<? extends RouterInterceptor> tClass) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
  Constructor<?>[] constructors=tClass.getConstructors();
  if (constructors == null) {
    return null;
  }
  for (  Constructor<?> constructor : constructors) {
    Class<?>[] parameterTypes=constructor.getParameterTypes();
    if (parameterTypes == null || parameterTypes.length == 0) {
      return (RouterInterceptor)constructor.newInstance();
    }
    if (parameterTypes.length == 1 && parameterTypes[0] == Application.class) {
      return (RouterInterceptor)constructor.newInstance(Component.getApplication());
    }
    if (parameterTypes.length == 1 && parameterTypes[0] == Context.class) {
      return (RouterInterceptor)constructor.newInstance(Component.getApplication());
    }
  }
  return null;
}
