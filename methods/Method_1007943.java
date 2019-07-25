/** 
 * The response body from the HTTP response containing an error, if any.
 * @deprecated use {@link #getResponseBody()} instead to avoid confusion with {@link Throwable#getMessage()}.
 */
@Deprecated @Nullable public String message(){
  return responseBody;
}
