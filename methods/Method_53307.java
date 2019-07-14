@Override public Relationship showFriendship(String sourceScreenName,String targetScreenName) throws TwitterException {
  return factory.createRelationship(get(conf.getRestBaseURL() + "friendships/show.json",getParameterArray("source_screen_name",sourceScreenName,"target_screen_name",targetScreenName)));
}
