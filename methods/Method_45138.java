public final boolean hasHeader(final HttpResponse httpResponse,final String name){
  return from(httpResponse.getHeaders().entrySet()).anyMatch(isForHeaderName(name));
}
