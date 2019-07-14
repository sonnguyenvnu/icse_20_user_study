@Override protected void onDelete(JSONObject json,StreamListener[] listeners) throws TwitterException, JSONException {
  for (  StreamListener listener : listeners) {
    JSONObject deletionNotice=json.getJSONObject("delete");
    if (deletionNotice.has("status")) {
      ((StatusListener)listener).onDeletionNotice(new StatusDeletionNoticeImpl(deletionNotice.getJSONObject("status")));
    }
 else {
      JSONObject directMessage=deletionNotice.getJSONObject("direct_message");
      ((UserStreamListener)listener).onDeletionNotice(ParseUtil.getLong("id",directMessage),ParseUtil.getLong("user_id",directMessage));
    }
  }
}
