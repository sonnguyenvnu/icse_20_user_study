private RequestBuilder addFormParams(RequestBuilder requestBuilder,Request request){
  if (request.getRequestBody() != null) {
    ByteArrayEntity entity=new ByteArrayEntity(request.getRequestBody().getBody());
    entity.setContentType(request.getRequestBody().getContentType());
    requestBuilder.setEntity(entity);
  }
  return requestBuilder;
}
