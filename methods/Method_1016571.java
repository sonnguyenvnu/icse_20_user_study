private static FormatterFunc apply(State state) throws Exception {
  Class<?> formatterClazz=getClass(state);
  Object formatter=formatterClazz.getConstructor(Properties.class).newInstance(state.getPreferences());
  Method method=formatterClazz.getMethod(FORMATTER_METHOD,String.class);
  return input -> (String)method.invoke(formatter,input);
}
