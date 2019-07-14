private String wrapExpression(String expressionString){
  return "#oauth2.throwOnError(" + expressionString + ")";
}
