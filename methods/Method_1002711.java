/** 
 * Writes the HTTP response of the specified  {@link HttpStatus} and closes the stream if the{@link HttpStatusClass} is not {@linkplain HttpStatusClass#INFORMATIONAL informational} (1xx).
 * @deprecated Use {@link HttpResponse#of(HttpStatus)}.
 */
@Deprecated default void respond(HttpStatus status){
  requireNonNull(status,"status");
  if (status.codeClass() == HttpStatusClass.INFORMATIONAL) {
    write(ResponseHeaders.of(status));
  }
 else   if (isContentAlwaysEmpty(status)) {
    write(ResponseHeaders.of(status));
    close();
  }
 else {
    respond(status,MediaType.PLAIN_TEXT_UTF_8,status.toHttpData());
  }
}
