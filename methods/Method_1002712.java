/** 
 * Writes the HTTP response of the specified  {@link HttpStatus} and closes the stream.
 * @param mediaType the {@link MediaType} of the response content
 * @param content the content of the response
 * @deprecated Use {@link HttpResponse#of(HttpStatus,MediaType,String)}.
 */
@Deprecated default void respond(HttpStatus status,MediaType mediaType,String content){
  requireNonNull(status,"status");
  requireNonNull(mediaType,"mediaType");
  requireNonNull(content,"content");
  respond(status,mediaType,content.getBytes(mediaType.charset().orElse(StandardCharsets.UTF_8)));
}
