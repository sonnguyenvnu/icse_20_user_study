@Override public String getTermsOfService() throws TwitterException {
  try {
    return get(conf.getRestBaseURL() + "help/tos.json").asJSONObject().getString("tos");
  }
 catch (  JSONException e) {
    throw new TwitterException(e);
  }
}
