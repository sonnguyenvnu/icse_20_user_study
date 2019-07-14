@Override public User createFriendship(String screenName,boolean follow) throws TwitterException {
  return factory.createUser(post(conf.getRestBaseURL() + "friendships/create.json",new HttpParameter[]{new HttpParameter("screen_name",screenName),new HttpParameter("follow",follow)}));
}
