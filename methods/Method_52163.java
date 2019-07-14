private String getCaughtExceptionsAsString(ASTCatchStatement stmt){
  StringBuilder sb=new StringBuilder();
  final String delim=" | ";
  for (  ASTType type : stmt.getCaughtExceptionTypeNodes()) {
    sb.append(type.getTypeImage()).append(delim);
  }
  sb.replace(sb.length() - 3,sb.length(),"");
  return sb.toString();
}
