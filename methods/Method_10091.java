/** 
 * Determines the user specified by the given user id has rewarded the data (article/comment/user) or not.
 * @param userId the specified user id
 * @param dataId the specified data id
 * @param type   the specified type
 * @return {@code true} if has rewared
 */
public boolean isRewarded(final String userId,final String dataId,final int type){
  final Query query=new Query();
  final List<Filter> filters=new ArrayList<>();
  filters.add(new PropertyFilter(Reward.SENDER_ID,FilterOperator.EQUAL,userId));
  filters.add(new PropertyFilter(Reward.DATA_ID,FilterOperator.EQUAL,dataId));
  filters.add(new PropertyFilter(Reward.TYPE,FilterOperator.EQUAL,type));
  query.setFilter(new CompositeFilter(CompositeFilterOperator.AND,filters));
  try {
    return 0 != rewardRepository.count(query);
  }
 catch (  final RepositoryException e) {
    LOGGER.log(Level.ERROR,"Determines reward error",e);
    return false;
  }
}
