@SuppressWarnings("unchecked") public Class<T> compile(String scriptName,Path scriptPath,String scriptText) throws IllegalAccessException, InstantiationException {
  return createClassLoader(scriptPath).parseClass(scriptText,scriptName);
}
