@SuppressWarnings("unchecked") public Class<T> compile(String scriptName,String scriptText) throws IllegalAccessException, InstantiationException {
  return createClassLoader(null).parseClass(scriptText,scriptName);
}
