/** 
 * 1.?????id 2.??date?id 3.??id????
 */
@Override public int deleteBeforeCollectTime(long collectTime){
  long startTime=System.currentTimeMillis();
  int deleteCount=0;
  try {
    int batchSize=10000;
    long minId=appClientCostTimeStatDao.getTableMinimumId();
    long maxId=appClientCostTimeStatDao.getMinimumIdByCollectTime(collectTime);
    if (minId > maxId) {
      return deleteCount;
    }
    long startId=minId;
    long endId=startId + batchSize;
    while (startId < maxId) {
      if (endId > maxId) {
        endId=maxId;
      }
      deleteCount+=appClientCostTimeStatDao.deleteByIds(startId,endId);
      startId+=batchSize;
      endId+=batchSize;
    }
  }
 catch (  Exception e) {
    logger.error(e.getMessage(),e);
  }
  logger.warn("batch delete before collectTime {} cost time is {} ms",collectTime,(System.currentTimeMillis() - startTime));
  return deleteCount;
}
