private Set<HttpMethod> resolveHttpMethods(String[] methods){
  Set<HttpMethod> httpMethods=new LinkedHashSet<>(methods.length);
  for (  String method : methods) {
    if (!StringUtils.hasText(method)) {
      continue;
    }
    HttpMethod httpMethod=resolve(method);
    httpMethods.add(httpMethod);
  }
  return httpMethods;
}
