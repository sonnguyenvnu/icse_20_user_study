public HttpMessageConverterHolder resolve(HttpRequest request,Class<?> parameterType){
  HttpMessageConverterHolder httpMessageConverterHolder=null;
  HttpHeaders httpHeaders=request.getHeaders();
  MediaType contentType=httpHeaders.getContentType();
  if (contentType == null) {
    contentType=MediaType.APPLICATION_OCTET_STREAM;
  }
  for (  HttpMessageConverter<?> converter : this.messageConverters) {
    if (converter instanceof GenericHttpMessageConverter) {
      GenericHttpMessageConverter genericConverter=(GenericHttpMessageConverter)converter;
      if (genericConverter.canRead(parameterType,parameterType,contentType)) {
        httpMessageConverterHolder=new HttpMessageConverterHolder(contentType,converter);
        break;
      }
    }
 else {
      if (converter.canRead(parameterType,contentType)) {
        httpMessageConverterHolder=new HttpMessageConverterHolder(contentType,converter);
        break;
      }
    }
  }
  return httpMessageConverterHolder;
}
