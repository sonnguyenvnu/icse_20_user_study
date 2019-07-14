private String[] getParameterTypes(Method method){
  Class<?>[] parameterTypes=method.getParameterTypes();
  return Stream.of(parameterTypes).map(Class::getName).toArray(length -> new String[length]);
}
