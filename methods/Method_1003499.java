private static Request rethrottle(RethrottleRequest rethrottleRequest,String firstPathPart){
  String endpoint=new EndpointBuilder().addPathPart(firstPathPart).addPathPart(rethrottleRequest.getTaskId().toString()).addPathPart("_rethrottle").build();
  Request request=new Request(HttpMethod.POST.name(),endpoint);
  Params params=new Params(request).withRequestsPerSecond(rethrottleRequest.getRequestsPerSecond());
  params.putParam("group_by","none");
  return request;
}
