/** 
 * ????????.
 * @param jobName ????
 * @param currentShardingTotalCount ??????
 */
public void setCurrentShardingTotalCount(final String jobName,final int currentShardingTotalCount){
  currentShardingTotalCountMap.put(jobName,currentShardingTotalCount);
}
