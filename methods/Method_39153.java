/** 
 * Resolves HTTP method name from method name. If method name or first camel-case word of a method equals to a HTTP method, it will be used as that HTTP methods.
 */
protected String resolveHttpMethodFromMethodName(final String methodName){
  int i=0;
  while (i < methodName.length()) {
    if (CharUtil.isUppercaseAlpha(methodName.charAt(i))) {
      break;
    }
    i++;
  }
  final String name=methodName.substring(0,i).toUpperCase();
  for (  final HttpMethod httpMethod : HttpMethod.values()) {
    if (httpMethod.equalsName(name)) {
      return httpMethod.name();
    }
  }
  return null;
}
