@Override public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
  if (map == null)   loadResourceDefine();
  HttpServletRequest request=((FilterInvocation)object).getHttpRequest();
  AntPathRequestMatcher matcher;
  String resUrl;
  for (Iterator<String> iter=map.keySet().iterator(); iter.hasNext(); ) {
    resUrl=iter.next();
    matcher=new AntPathRequestMatcher(resUrl);
    if (matcher.matches(request)) {
      return map.get(resUrl);
    }
  }
  return null;
}
