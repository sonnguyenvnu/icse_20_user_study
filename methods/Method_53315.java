@Override public PagableResponseList<User> getFollowersList(String screenName,long cursor,int count,boolean skipStatus,boolean includeUserEntities) throws TwitterException {
  return factory.createPagableUserList(get(conf.getRestBaseURL() + "followers/list.json",new HttpParameter("screen_name",screenName),new HttpParameter("cursor",cursor),new HttpParameter("count",count),new HttpParameter("skip_status",skipStatus),new HttpParameter("include_user_entities",includeUserEntities)));
}
