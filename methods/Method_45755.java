protected static boolean isBeanPropertyReadMethod(Method method){
  return method != null && Modifier.isPublic(method.getModifiers()) && !Modifier.isStatic(method.getModifiers()) && method.getReturnType() != void.class && method.getDeclaringClass() != Object.class && method.getParameterTypes().length == 0 && (method.getName().startsWith("get") || method.getName().startsWith("is")) && (!"get".equals(method.getName()) && !"is".equals(method.getName()));
}
