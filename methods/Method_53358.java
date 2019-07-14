@Override public ResponseList<Status> getUserListStatuses(long ownerId,String slug,Paging paging) throws TwitterException {
  return factory.createStatusList(get(conf.getRestBaseURL() + "lists/statuses.json",mergeParameters(paging.asPostParameterArray(Paging.SMCP,Paging.COUNT),new HttpParameter[]{new HttpParameter("owner_id",ownerId),new HttpParameter("slug",slug)})));
}
