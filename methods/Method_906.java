private String getInvocationName(AbstractJavaNode javaNode){
  Token token=(Token)javaNode.jjtGetFirstToken();
  StringBuilder sb=new StringBuilder(token.image).append(token.image);
  while (token.next != null && token.next.image != null && !METHOD_EQUALS.equals(token.next.image)) {
    token=token.next;
    sb.append(token.image);
  }
  if (sb.charAt(sb.length() - 1) == StringAndCharConstants.DOT) {
    sb.deleteCharAt(sb.length() - 1);
  }
  return sb.toString();
}
