public static Request exists(GetRequest getRequest){
  return getStyleRequest(HttpMethod.HEAD.name(),getRequest);
}
