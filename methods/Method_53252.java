/** 
 * Returns int value of "Retry-After" response header (Search API) or seconds_until_reset (REST API). An application that exceeds the rate limitations of the Search API will receive HTTP 420 response codes to requests. It is a best practice to watch for this error condition and honor the Retry-After header that instructs the application when it is safe to continue. The Retry-After header's value is the number of seconds your application should wait before submitting another query (for example: Retry-After: 67).<br> Check if getStatusCode() == 503 before calling this method to ensure that you are actually exceeding rate limitation with query apis.<br>
 * @return instructs the application when it is safe to continue in seconds
 * @see <a href="https://dev.twitter.com/docs/rate-limiting">Rate Limiting | Twitter Developers</a>
 * @since Twitter4J 2.1.0
 */
public int getRetryAfter(){
  int retryAfter=-1;
  if (this.statusCode == 400) {
    RateLimitStatus rateLimitStatus=getRateLimitStatus();
    if (rateLimitStatus != null) {
      retryAfter=rateLimitStatus.getSecondsUntilReset();
    }
  }
 else   if (this.statusCode == ENHANCE_YOUR_CLAIM) {
    try {
      String retryAfterStr=response.getResponseHeader("Retry-After");
      if (retryAfterStr != null) {
        retryAfter=Integer.valueOf(retryAfterStr);
      }
    }
 catch (    NumberFormatException ignore) {
    }
  }
  return retryAfter;
}
