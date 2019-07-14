/** 
 * Tests if the exception is caused by rate limitation exceed
 * @return if the exception is caused by rate limitation exceed
 * @see <a href="https://dev.twitter.com/docs/rate-limiting">Rate Limiting | Twitter Developers</a>
 * @since Twitter4J 2.1.2
 */
public boolean exceededRateLimitation(){
  return (statusCode == 400 && getRateLimitStatus() != null) || (statusCode == ENHANCE_YOUR_CLAIM) || (statusCode == TOO_MANY_REQUESTS);
}
