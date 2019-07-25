private static void reject(HttpMethod method,HttpMethods supported) throws HttpRequestMethodNotSupportedException {
  Set<String> stringMethods=supported.butWithout(method).stream().map(HttpMethod::name).collect(Collectors.toSet());
  throw new HttpRequestMethodNotSupportedException(method.name(),stringMethods);
}
