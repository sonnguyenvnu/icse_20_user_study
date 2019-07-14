@Override protected void onSender(JSONObject json,StreamListener[] listeners) throws TwitterException {
  for (  StreamListener listener : listeners) {
    ((UserStreamListener)listener).onDirectMessage(new DirectMessageJSONImpl(json));
  }
}
