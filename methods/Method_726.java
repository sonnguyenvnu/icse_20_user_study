/** 
 * Return the content type to set the response to. This implementation always returns "application/javascript".
 * @param contentType the content type selected through content negotiation
 * @param request     the current request
 * @param response    the current response
 * @return the content type to set the response to
 */
protected MediaType getContentType(MediaType contentType,ServerHttpRequest request,ServerHttpResponse response){
  return new MediaType("application","javascript");
}
