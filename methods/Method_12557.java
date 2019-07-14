private String getEndpointLocalPath(String endpointPathPattern,ServerHttpRequest request){
  String pathWithinApplication=request.getPath().pathWithinApplication().value();
  return this.pathMatcher.extractPathWithinPattern(endpointPathPattern,pathWithinApplication);
}
