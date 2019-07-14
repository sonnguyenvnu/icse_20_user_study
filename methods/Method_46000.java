public static String generateCommandKey(String interfaceId,Method method){
  StringBuilder builder=new StringBuilder(interfaceId).append("#").append(method.getName()).append("(");
  if (method.getParameterTypes().length > 0) {
    for (    Class<?> parameterType : method.getParameterTypes()) {
      builder.append(parameterType.getSimpleName()).append(",");
    }
    builder.deleteCharAt(builder.length() - 1);
  }
  return builder.append(")").toString();
}
