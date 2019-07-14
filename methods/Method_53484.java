/** 
 * Returns a stream of random sample of all public statuses. The default access level provides a small proportion of the Firehose. The "Gardenhose" access level provides a proportion more suitable for data mining and research applications that desire a larger proportion to be statistically significant sample. <p> Only returns tweets in the given languages
 * @return StatusStream
 * @throws TwitterException when Twitter service or network is unavailable
 * @see twitter4j.StatusStream
 * @see <a href="https://dev.twitter.com/docs/streaming-api/methods">Streaming API: Methods statuses/sample</a>
 * @since Twitter4J 2.0.10
 */
StatusStream getSampleStream(String language) throws TwitterException {
  ensureAuthorizationEnabled();
  try {
    return new StatusStreamImpl(getDispatcher(),http.get(conf.getStreamBaseURL() + "statuses/sample.json?" + stallWarningsGetParam + "&language=" + language,null,auth,null),conf);
  }
 catch (  IOException e) {
    throw new TwitterException(e);
  }
}
