/** 
 * Returns a new builder with the specified  {@link HttpStatus}.
 */
static ResponseHeadersBuilder builder(HttpStatus status){
  requireNonNull(status,"status");
  return builder().add(HttpHeaderNames.STATUS,status.codeAsText());
}
