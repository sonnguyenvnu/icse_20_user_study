@Override public ResponseList<Status> getUserListStatuses(String ownerScreenName,String slug,Paging paging) throws TwitterException {
  return factory.createStatusList(get(conf.getRestBaseURL() + "lists/statuses.json",mergeParameters(paging.asPostParameterArray(Paging.SMCP,Paging.COUNT),new HttpParameter[]{new HttpParameter("owner_screen_name",ownerScreenName),new HttpParameter("slug",slug)})));
}
