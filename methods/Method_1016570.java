private static FormatterFunc apply(EclipseBasedStepBuilder.State state) throws Exception {
  Class<?> formatterClazz=getClass(state);
  Object formatter=formatterClazz.getConstructor(Properties.class).newInstance(state.getPreferences());
  Method method=formatterClazz.getMethod(FORMATTER_METHOD,String.class);
  return input -> {
    try {
      return (String)method.invoke(formatter,input);
    }
 catch (    InvocationTargetException exceptionWrapper) {
      Throwable throwable=exceptionWrapper.getTargetException();
      Exception exception=(throwable instanceof Exception) ? (Exception)throwable : null;
      throw (null == exception) ? exceptionWrapper : exception;
    }
  }
;
}
