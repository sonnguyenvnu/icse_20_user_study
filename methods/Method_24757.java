static private String makeCompletion(Method method){
  Class<?>[] types=method.getParameterTypes();
  StringBuilder compBuilder=new StringBuilder();
  compBuilder.append(method.getName());
  compBuilder.append('(');
  for (int i=0; i < types.length; i++) {
    if (i < types.length - 1) {
      compBuilder.append(',');
    }
  }
  if (types.length == 1) {
    compBuilder.append(' ');
  }
  compBuilder.append(')');
  return compBuilder.toString();
}
