protected void customizeRequest(MutableHttpServerRequest httpServerRequest,RestMethodMetadata dubboRestMethodMetadata,RequestMetadata clientMetadata){
  RequestMetadata dubboRequestMetadata=dubboRestMethodMetadata.getRequest();
  String pathPattern=dubboRequestMetadata.getPath();
  Map<String,String> pathVariables=pathMatcher.extractUriTemplateVariables(pathPattern,httpServerRequest.getPath());
  if (!CollectionUtils.isEmpty(pathVariables)) {
    httpServerRequest.params(pathVariables);
  }
}
