private RequestMatcher createRequestMatcherWithResource(final String operation,final Resource resource){
  try {
    Method operationMethod=Moco.class.getMethod(operation,Resource.class);
    Object result=operationMethod.invoke(null,resource);
    Optional<RequestMatcher> matcher=createRequestMatcher(result);
    if (matcher.isPresent()) {
      return matcher.get();
    }
    throw new IllegalArgumentException("unknown operation [" + operation + "]");
  }
 catch (  Exception e) {
    throw new RuntimeException(e);
  }
}
