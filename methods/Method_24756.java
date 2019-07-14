static private String makeLabel(Method method){
  Class<?>[] types=method.getParameterTypes();
  StringBuilder labelBuilder=new StringBuilder();
  labelBuilder.append("<html>");
  labelBuilder.append(method.getName());
  labelBuilder.append('(');
  for (int i=0; i < types.length; i++) {
    labelBuilder.append(types[i].getSimpleName());
    if (i < types.length - 1) {
      labelBuilder.append(',');
    }
  }
  labelBuilder.append(")");
  if (method.getReturnType() != null) {
    labelBuilder.append(" : ");
    labelBuilder.append(method.getReturnType().getSimpleName());
  }
  labelBuilder.append(" - <font color=#777777>");
  labelBuilder.append(method.getDeclaringClass().getSimpleName());
  labelBuilder.append("</font>");
  labelBuilder.append("</html>");
  return labelBuilder.toString();
}
