/** 
 * Returns a new  {@link HttpMethods} with the given {@link HttpMethod}s added.
 * @param method must not be {@literal null}.
 * @return
 */
default HttpMethods and(HttpMethod... method){
  return of(Stream.concat(stream(),Arrays.stream(method)).collect(Collectors.toSet()));
}
