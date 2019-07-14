@Override public DirectMessage sendDirectMessage(long recipientId,String text,QuickReply... quickReplies) throws TwitterException {
  try {
    return factory.createDirectMessage(post(conf.getRestBaseURL() + "direct_messages/events/new.json",createMessageCreateJsonObject(recipientId,text,-1L,null,quickReplies)));
  }
 catch (  JSONException e) {
    throw new TwitterException(e);
  }
}
