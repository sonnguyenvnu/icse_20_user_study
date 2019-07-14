private static String newArgument(Collection<String> initializers){
  StringBuilder expression=new StringBuilder();
  if (initializers.size() > 1) {
    expression.append('{');
  }
  Joiner.on(", ").appendTo(expression,initializers);
  if (initializers.size() > 1) {
    expression.append('}');
  }
  return expression.toString();
}
