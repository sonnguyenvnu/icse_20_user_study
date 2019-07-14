/** 
 * a kind of copy factory method constructs an AsyncTwitter from Twitter instance
 * @param twitter Twitter instance
 * @return an instance
 */
public AsyncTwitter getInstance(Twitter twitter){
  return new AsyncTwitterImpl(twitter.getConfiguration(),twitter.getAuthorization());
}
