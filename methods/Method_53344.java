@Override public ResponseList<User> getUserSuggestions(String categorySlug) throws TwitterException {
  HttpResponse res;
  try {
    res=get(conf.getRestBaseURL() + "users/suggestions/" + URLEncoder.encode(categorySlug,"UTF-8") + ".json");
  }
 catch (  UnsupportedEncodingException e) {
    throw new RuntimeException(e);
  }
  return factory.createUserListFromJSONArray_Users(res);
}
