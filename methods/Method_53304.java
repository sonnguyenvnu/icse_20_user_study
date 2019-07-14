@Override public User destroyFriendship(String screenName) throws TwitterException {
  return factory.createUser(post(conf.getRestBaseURL() + "friendships/destroy.json",new HttpParameter[]{new HttpParameter("screen_name",screenName)}));
}
