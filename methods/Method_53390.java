private UserList updateUserList(String newListName,boolean isPublicList,String newDescription,HttpParameter... params) throws TwitterException {
  List<HttpParameter> httpParams=new ArrayList<HttpParameter>();
  Collections.addAll(httpParams,params);
  if (newListName != null) {
    httpParams.add(new HttpParameter("name",newListName));
  }
  httpParams.add(new HttpParameter("mode",isPublicList ? "public" : "private"));
  if (newDescription != null) {
    httpParams.add(new HttpParameter("description",newDescription));
  }
  return factory.createAUserList(post(conf.getRestBaseURL() + "lists/update.json",httpParams.toArray(new HttpParameter[httpParams.size()])));
}
