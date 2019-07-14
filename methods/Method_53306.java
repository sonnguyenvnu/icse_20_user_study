@Override public Relationship showFriendship(long sourceId,long targetId) throws TwitterException {
  return factory.createRelationship(get(conf.getRestBaseURL() + "friendships/show.json",new HttpParameter("source_id",sourceId),new HttpParameter("target_id",targetId)));
}
