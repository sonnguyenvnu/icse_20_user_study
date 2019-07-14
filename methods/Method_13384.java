private static Set<ParamExpression> parseExpressions(String... params){
  Set<ParamExpression> expressions=new LinkedHashSet<>();
  for (  String param : params) {
    expressions.add(new ParamExpression(param));
  }
  return expressions;
}
