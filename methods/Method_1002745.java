/** 
 * Returns a new builder with the specified  {@link HttpMethod} and {@code path}.
 */
static RequestHeadersBuilder builder(HttpMethod method,String path){
  requireNonNull(method,"method");
  requireNonNull(path,"path");
  return builder().add(HttpHeaderNames.METHOD,method.name()).add(HttpHeaderNames.PATH,path);
}
