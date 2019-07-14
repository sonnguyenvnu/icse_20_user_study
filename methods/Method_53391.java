@Override public UserList createUserList(String listName,boolean isPublicList,String description) throws TwitterException {
  List<HttpParameter> httpParams=new ArrayList<HttpParameter>();
  httpParams.add(new HttpParameter("name",listName));
  httpParams.add(new HttpParameter("mode",isPublicList ? "public" : "private"));
  if (description != null) {
    httpParams.add(new HttpParameter("description",description));
  }
  return factory.createAUserList(post(conf.getRestBaseURL() + "lists/create.json",httpParams.toArray(new HttpParameter[httpParams.size()])));
}
