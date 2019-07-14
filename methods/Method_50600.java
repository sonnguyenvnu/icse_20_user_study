private static String getOperationString(ASTMethod node){
  StringBuilder sb=new StringBuilder();
  sb.append(node.getImage()).append('(');
  List<TypeInfo> paramTypes=node.getNode().getMethodInfo().getParameterTypes();
  if (!paramTypes.isEmpty()) {
    sb.append(paramTypes.get(0).getApexName());
    for (int i=1; i < paramTypes.size(); i++) {
      sb.append(",").append(paramTypes.get(i).getApexName());
    }
  }
  sb.append(')');
  return sb.toString();
}
