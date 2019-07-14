protected static boolean isBeanPropertyWriteMethod(Method method){
  return method != null && Modifier.isPublic(method.getModifiers()) && !Modifier.isStatic(method.getModifiers()) && method.getDeclaringClass() != Object.class && method.getParameterTypes().length == 1 && method.getName().startsWith("set") && !"set".equals(method.getName());
}
