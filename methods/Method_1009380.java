public static Method getter(Class<?> type,String field) throws NoSuchMethodException {
  String methodName="get" + formatMethodName(field);
  return type.getMethod(methodName);
}
