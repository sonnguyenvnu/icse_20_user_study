@Override public Relationship updateFriendship(long userId,boolean enableDeviceNotification,boolean retweets) throws TwitterException {
  return factory.createRelationship((post(conf.getRestBaseURL() + "friendships/update.json",new HttpParameter("user_id",userId),new HttpParameter("device",enableDeviceNotification),new HttpParameter("retweets",retweets))));
}
