@Override protected void onLimit(JSONObject json,StreamListener[] listeners) throws TwitterException, JSONException {
  for (  StreamListener listener : listeners) {
    ((StatusListener)listener).onTrackLimitationNotice(ParseUtil.getInt("track",json.getJSONObject("limit")));
  }
}
