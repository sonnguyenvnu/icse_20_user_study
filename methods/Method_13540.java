@Override public void service(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
  String serviceName=resolveServiceName(request);
  String restPath=substringAfter(request.getRequestURI(),serviceName);
  repository.initialize(serviceName);
  RequestMetadata clientMetadata=buildRequestMetadata(request,restPath);
  DubboRestServiceMetadata dubboRestServiceMetadata=repository.get(serviceName,clientMetadata);
  if (dubboRestServiceMetadata == null) {
    throw new ServletException("DubboServiceMetadata can't be found!");
  }
  RestMethodMetadata dubboRestMethodMetadata=dubboRestServiceMetadata.getRestMethodMetadata();
  GenericService genericService=serviceFactory.create(dubboRestServiceMetadata,dubboTranslatedAttributes);
  byte[] body=getRequestBody(request);
  MutableHttpServerRequest httpServerRequest=new MutableHttpServerRequest(new HttpRequestAdapter(request),body);
  DubboGenericServiceExecutionContext context=contextFactory.create(dubboRestMethodMetadata,httpServerRequest);
  Object result=null;
  GenericException exception=null;
  try {
    result=genericService.$invoke(context.getMethodName(),context.getParameterTypes(),context.getParameters());
  }
 catch (  GenericException e) {
    exception=e;
  }
  response.getWriter().println(result);
}
