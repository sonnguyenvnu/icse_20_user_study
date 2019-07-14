/** 
 * Gets rewarded count.
 * @param dataId the specified data id
 * @param type   the specified type
 * @return rewarded count
 */
public long rewardedCount(final String dataId,final int type){
  final Query query=new Query();
  final List<Filter> filters=new ArrayList<>();
  filters.add(new PropertyFilter(Reward.DATA_ID,FilterOperator.EQUAL,dataId));
  filters.add(new PropertyFilter(Reward.TYPE,FilterOperator.EQUAL,type));
  query.setFilter(new CompositeFilter(CompositeFilterOperator.AND,filters));
  try {
    return rewardRepository.count(query);
  }
 catch (  final RepositoryException e) {
    LOGGER.log(Level.ERROR,"Rewarded count error",e);
    return 0;
  }
}
