/** 
 * Writes the HTTP response of the specified  {@link HttpStatus} and closes the stream.
 * @param mediaType the {@link MediaType} of the response content
 * @param content the content of the response
 * @param trailers the HTTP trailers
 * @deprecated Use {@link HttpResponse#of(HttpStatus,MediaType,HttpData,HttpHeaders)}.
 */
@Deprecated default void respond(HttpStatus status,MediaType mediaType,HttpData content,HttpHeaders trailers){
  requireNonNull(status,"status");
  requireNonNull(mediaType,"mediaType");
  requireNonNull(content,"content");
  requireNonNull(trailers,"trailers");
  final ResponseHeaders headers=ResponseHeaders.of(status,HttpHeaderNames.CONTENT_TYPE,mediaType,HttpHeaderNames.CONTENT_LENGTH,content.length());
  if (isContentAlwaysEmptyWithValidation(status,content,trailers)) {
    ReferenceCountUtil.safeRelease(content);
    write(headers);
  }
 else {
    write(headers);
    if (!content.isEmpty()) {
      write(content);
    }
  }
  if (!trailers.isEmpty()) {
    write(trailers);
  }
  close();
}
