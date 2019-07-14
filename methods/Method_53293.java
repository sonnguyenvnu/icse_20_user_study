@Override public DirectMessage sendDirectMessage(long recipientId,String text,long mediaId) throws TwitterException {
  try {
    return factory.createDirectMessage(post(conf.getRestBaseURL() + "direct_messages/events/new.json",createMessageCreateJsonObject(recipientId,text,mediaId,null)));
  }
 catch (  JSONException e) {
    throw new TwitterException(e);
  }
}
