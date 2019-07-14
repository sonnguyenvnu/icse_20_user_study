private static Set<HeaderExpression> parseExpressions(String... headers){
  Set<HeaderExpression> expressions=new LinkedHashSet<>();
  for (  String header : headers) {
    HeaderExpression expr=new HeaderExpression(header);
    if (HttpHeaders.ACCEPT.equalsIgnoreCase(expr.name) || HttpHeaders.CONTENT_TYPE.equalsIgnoreCase(expr.name)) {
      continue;
    }
    expressions.add(expr);
  }
  return expressions;
}
