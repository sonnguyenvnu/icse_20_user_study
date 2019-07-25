/** 
 * Writes the HTTP response of the specified  {@code statusCode} and closes the stream if the{@link HttpStatusClass} is not {@linkplain HttpStatusClass#INFORMATIONAL informational} (1xx).
 * @deprecated Use {@link HttpResponse#of(int)}.
 */
@Deprecated default void respond(int statusCode){
  respond(HttpStatus.valueOf(statusCode));
}
