/** 
 * Returns a status stream of all public statuses containing links. Available only to approved parties and requires a signed agreement to access. Please do not contact us about access to the links stream. If your service warrants access to it, we'll contact you.
 * @param count Indicates the number of previous statuses to stream before transitioning to the live stream.
 * @return StatusStream
 * @throws TwitterException when Twitter service or network is unavailable
 * @see twitter4j.StatusStream
 * @see <a href="https://dev.twitter.com/docs/streaming-api/methods">Streaming API Methods statuses/links</a>
 * @since Twitter4J 2.1.1
 */
StatusStream getLinksStream(int count) throws TwitterException {
  ensureAuthorizationEnabled();
  return getCountStream("statuses/links.json",count);
}
