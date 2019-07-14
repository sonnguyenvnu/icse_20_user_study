public static String getMethodSignature(String name,Class<?>... parameterTypes){
  StringBuilder builder=new StringBuilder(name);
  if (!(name.equals(CLINIT) || name.equals(INIT))) {
    builder.append('(');
    if (parameterTypes != null && parameterTypes.length > 0) {
      builder.append(parameterTypes[0].getName());
      for (int i=1; i < parameterTypes.length; i++) {
        builder.append(", ").append(parameterTypes[i].getName());
      }
    }
    builder.append(')');
  }
  return builder.toString();
}
