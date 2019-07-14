@Override public String getPrivacyPolicy() throws TwitterException {
  try {
    return get(conf.getRestBaseURL() + "help/privacy.json").asJSONObject().getString("privacy");
  }
 catch (  JSONException e) {
    throw new TwitterException(e);
  }
}
