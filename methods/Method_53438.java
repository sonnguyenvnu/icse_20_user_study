void handleNextElement(final StreamListener[] listeners,final RawStreamListener[] rawStreamListeners) throws TwitterException {
  if (!streamAlive) {
    throw new IllegalStateException("Stream already closed.");
  }
  try {
    String line=br.readLine();
    if (null == line) {
      throw new IOException("the end of the stream has been reached");
    }
    dispatcher.invokeLater(new StreamEvent(line){
      @Override public void run(){
        try {
          if (rawStreamListeners.length > 0) {
            onMessage(line,rawStreamListeners);
          }
          line=parseLine(line);
          if (line != null && line.length() > 0) {
            if (listeners.length > 0) {
              if (CONF.isJSONStoreEnabled()) {
                TwitterObjectFactory.clearThreadLocalMap();
              }
              JSONObject json=new JSONObject(line);
              JSONObjectType.Type event=JSONObjectType.determine(json);
              if (logger.isDebugEnabled()) {
                logger.debug("Received:",CONF.getHttpClientConfiguration().isPrettyDebugEnabled() ? json.toString(1) : json.toString());
              }
switch (event) {
case SENDER:
                onSender(json,listeners);
              break;
case STATUS:
            onStatus(json,listeners);
          break;
case DIRECT_MESSAGE:
        onDirectMessage(json,listeners);
      break;
case DELETE:
    onDelete(json,listeners);
  break;
case LIMIT:
onLimit(json,listeners);
break;
case STALL_WARNING:
onStallWarning(json,listeners);
break;
case SCRUB_GEO:
onScrubGeo(json,listeners);
break;
case FRIENDS:
onFriends(json,listeners);
break;
case FAVORITE:
onFavorite(json.getJSONObject("source"),json.getJSONObject("target"),json.getJSONObject("target_object"),listeners);
break;
case UNFAVORITE:
onUnfavorite(json.getJSONObject("source"),json.getJSONObject("target"),json.getJSONObject("target_object"),listeners);
break;
case FOLLOW:
onFollow(json.getJSONObject("source"),json.getJSONObject("target"),listeners);
break;
case UNFOLLOW:
onUnfollow(json.getJSONObject("source"),json.getJSONObject("target"),listeners);
break;
case USER_LIST_MEMBER_ADDED:
onUserListMemberAddition(json.getJSONObject("target"),json.getJSONObject("source"),json.getJSONObject("target_object"),listeners);
break;
case USER_LIST_MEMBER_DELETED:
onUserListMemberDeletion(json.getJSONObject("target"),json.getJSONObject("source"),json.getJSONObject("target_object"),listeners);
break;
case USER_LIST_SUBSCRIBED:
onUserListSubscription(json.getJSONObject("source"),json.getJSONObject("target"),json.getJSONObject("target_object"),listeners);
break;
case USER_LIST_UNSUBSCRIBED:
onUserListUnsubscription(json.getJSONObject("source"),json.getJSONObject("target"),json.getJSONObject("target_object"),listeners);
break;
case USER_LIST_CREATED:
onUserListCreation(json.getJSONObject("source"),json.getJSONObject("target_object"),listeners);
break;
case USER_LIST_UPDATED:
onUserListUpdated(json.getJSONObject("source"),json.getJSONObject("target_object"),listeners);
break;
case USER_LIST_DESTROYED:
onUserListDestroyed(json.getJSONObject("source"),json.getJSONObject("target_object"),listeners);
break;
case USER_UPDATE:
onUserUpdate(json.getJSONObject("source"),json.getJSONObject("target"),listeners);
break;
case USER_DELETE:
onUserDeletion(json.getLong("target"),listeners);
break;
case USER_SUSPEND:
onUserSuspension(json.getLong("target"),listeners);
break;
case BLOCK:
onBlock(json.getJSONObject("source"),json.getJSONObject("target"),listeners);
break;
case UNBLOCK:
onUnblock(json.getJSONObject("source"),json.getJSONObject("target"),listeners);
break;
case RETWEETED_RETWEET:
onRetweetedRetweet(json.getJSONObject("source"),json.getJSONObject("target"),json.getJSONObject("target_object"),listeners);
break;
case FAVORITED_RETWEET:
onFavoritedRetweet(json.getJSONObject("source"),json.getJSONObject("target"),json.getJSONObject("target_object"),listeners);
break;
case QUOTED_TWEET:
onQuotedTweet(json.getJSONObject("source"),json.getJSONObject("target"),json.getJSONObject("target_object"),listeners);
break;
case DISCONNECTION:
onDisconnectionNotice(line,listeners);
break;
case MUTE:
onMute(json.getJSONObject("source"),json.getJSONObject("target"),listeners);
break;
case UNMUTE:
onUnmute(json.getJSONObject("source"),json.getJSONObject("target"),listeners);
break;
case UNKNOWN:
default :
logger.warn("Received unknown event:",CONF.getHttpClientConfiguration().isPrettyDebugEnabled() ? json.toString(1) : json.toString());
}
}
}
}
 catch (Exception ex) {
onException(ex,listeners);
}
}
}
);
}
 catch (IOException ioe) {
try {
is.close();
}
 catch (IOException ignore) {
}
boolean isUnexpectedException=streamAlive;
streamAlive=false;
onClose();
if (isUnexpectedException) {
throw new TwitterException("Stream closed.",ioe);
}
}
}
