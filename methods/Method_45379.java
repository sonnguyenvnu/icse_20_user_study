private static RequestMatcher wrapRequestMatcher(final RequestSetting request,final ImmutableList<RequestMatcher> matchers){
switch (matchers.size()) {
case 0:
    throw new IllegalArgumentException("illegal request setting:" + request);
case 1:
  return matchers.get(0);
default :
return new AndRequestMatcher(matchers);
}
}
