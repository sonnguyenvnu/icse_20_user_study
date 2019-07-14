@Override public ResponseList<User> getMemberSuggestions(String categorySlug) throws TwitterException {
  HttpResponse res;
  try {
    res=get(conf.getRestBaseURL() + "users/suggestions/" + URLEncoder.encode(categorySlug,"UTF-8") + "/members.json");
  }
 catch (  UnsupportedEncodingException e) {
    throw new RuntimeException(e);
  }
  return factory.createUserListFromJSONArray(res);
}
