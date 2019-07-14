private HttpEntity asEntity(final ContentResource resource,final Request request){
  return new ByteArrayEntity(resource.readFor(request).getContent(),getContentType((HttpRequest)request));
}
