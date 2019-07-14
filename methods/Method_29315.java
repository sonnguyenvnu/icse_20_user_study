/** 
 * ??trigger?????trigger name?trigger group
 * @param queryString   trigger name?trigger group????
 * @return
 */
@Override public List<TriggerInfo> searchTriggerByNameOrGroup(String queryString){
  List<TriggerInfo> matchTriggers=null;
  try {
    matchTriggers=quartzDao.searchTriggerByNameOrGroup(queryString);
  }
 catch (  Exception e) {
    logger.error("queryString: {}",queryString,e);
  }
  return matchTriggers;
}
