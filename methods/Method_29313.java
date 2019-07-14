@Override public List<TriggerInfo> getTriggersByNameOrGroup(String query){
  return quartzDao.searchTriggerByNameOrGroup(query);
}
