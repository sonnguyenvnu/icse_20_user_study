private static Set<ProduceMediaTypeExpression> parseExpressions(String[] produces,String[] headers){
  Set<ProduceMediaTypeExpression> result=new LinkedHashSet<>();
  if (headers != null) {
    for (    String header : headers) {
      HeaderExpression expr=new HeaderExpression(header);
      if (HttpHeaders.ACCEPT.equalsIgnoreCase(expr.name) && expr.value != null) {
        for (        MediaType mediaType : MediaType.parseMediaTypes(expr.value)) {
          result.add(new ProduceMediaTypeExpression(mediaType,expr.negated));
        }
      }
    }
  }
  for (  String produce : produces) {
    result.add(new ProduceMediaTypeExpression(produce));
  }
  return result;
}
