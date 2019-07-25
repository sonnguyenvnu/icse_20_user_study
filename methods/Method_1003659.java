public void handle(Context context){
  ratpack.http.HttpMethod requestMethod=context.getRequest().getMethod();
  if (requestMethod == method || requestMethod.name(method.getName())) {
    context.next();
  }
 else   if (requestMethod.isOptions()) {
    Response response=context.getResponse();
    response.getHeaders().add(HttpHeaderConstants.ALLOW,method);
    response.status(200).send();
  }
 else {
    context.clientError(405);
  }
}
