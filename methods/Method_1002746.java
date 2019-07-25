/** 
 * Returns a new builder with the specified  {@code statusCode}.
 */
static ResponseHeadersBuilder builder(int statusCode){
  return builder(HttpStatus.valueOf(statusCode));
}
