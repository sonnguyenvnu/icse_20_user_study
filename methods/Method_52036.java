/** 
 * Returns a normalized method name (not Java-canonical!). 
 */
private static String getOperationName(String methodName,ASTFormalParameters params){
  StringBuilder sb=new StringBuilder();
  sb.append(methodName);
  sb.append('(');
  boolean first=true;
  for (  ASTFormalParameter param : params) {
    if (!first) {
      sb.append(", ");
    }
    first=false;
    sb.append(param.getTypeNode().getTypeImage());
    if (param.isVarargs()) {
      sb.append("...");
    }
  }
  sb.append(')');
  return sb.toString();
}
