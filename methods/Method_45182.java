private HttpResponseSetting requestByHttpMethod(final HttpMethod method,final RequestMatcher matcher){
  return request(and(by(method(method)),matcher));
}
