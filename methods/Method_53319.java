@Override public User updateProfile(String name,String url,String location,String description) throws TwitterException {
  List<HttpParameter> profile=new ArrayList<HttpParameter>(4);
  addParameterToList(profile,"name",name);
  addParameterToList(profile,"url",url);
  addParameterToList(profile,"location",location);
  addParameterToList(profile,"description",description);
  return factory.createUser(post(conf.getRestBaseURL() + "account/update_profile.json",profile.toArray(new HttpParameter[profile.size()])));
}
