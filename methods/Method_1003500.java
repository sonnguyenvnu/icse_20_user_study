public static Request analyze(AnalyzeRequest request) throws IOException {
  EndpointBuilder builder=new EndpointBuilder();
  String index=request.index();
  if (index != null) {
    builder.addPathPart(index);
  }
  builder.addPathPartAsIs("_analyze");
  Request req=new Request(HttpMethod.GET.name(),builder.build());
  req.setEntity(createEntity(request,REQUEST_BODY_CONTENT_TYPE));
  return req;
}
