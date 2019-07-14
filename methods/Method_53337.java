@Override public ResponseList<User> searchUsers(String query,int page) throws TwitterException {
  return factory.createUserList(get(conf.getRestBaseURL() + "users/search.json",new HttpParameter("q",query),new HttpParameter("per_page",20),new HttpParameter("page",page)));
}
