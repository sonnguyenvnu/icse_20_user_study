@Override public DirectMessage sendDirectMessage(long recipientId,String text,String quickReplyResponse) throws TwitterException {
  try {
    return factory.createDirectMessage(post(conf.getRestBaseURL() + "direct_messages/events/new.json",createMessageCreateJsonObject(recipientId,text,-1L,quickReplyResponse)));
  }
 catch (  JSONException e) {
    throw new TwitterException(e);
  }
}
