public OAuthConsumer createConsumer(String consumerKey,String consumerSecret){
  return new CommonsHttpOAuthConsumer(consumerKey,consumerSecret);
}
