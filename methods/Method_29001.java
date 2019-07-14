/** 
 * ????
 * @param exception
 * @param hostPort
 * @param currentTime
 * @param clientExceptionType???jedis??client?
 */
public static void collectException(Exception exception,String hostPort,long currentTime,ClientExceptionType clientExceptionType){
  if (exception == null) {
    return;
  }
  try {
    String currentMinute=ClientReportConstant.getCollectTimeSDf().format(new Date());
    ExceptionModel jedisExceptionModel=new ExceptionModel();
    String exceptionClassName=exception.getClass().getName();
    jedisExceptionModel.setExceptionClass(exceptionClassName);
    jedisExceptionModel.setHostPort(hostPort);
    jedisExceptionModel.setClientExceptionType(clientExceptionType);
    if (DATA_EXCEPTION_MAP_ALL.containsKey(currentMinute)) {
      DATA_EXCEPTION_MAP_ALL.get(currentMinute).getAndIncrement(jedisExceptionModel);
    }
 else {
      AtomicLongMap<ExceptionModel> dataExcpetionMap=AtomicLongMap.create();
      dataExcpetionMap.getAndIncrement(jedisExceptionModel);
      AtomicLongMap<ExceptionModel> currentDataExcpetionMap=DATA_EXCEPTION_MAP_ALL.putIfAbsent(currentMinute,dataExcpetionMap);
      if (currentDataExcpetionMap != null) {
        currentDataExcpetionMap.getAndIncrement(jedisExceptionModel);
      }
    }
  }
 catch (  Exception e) {
    logger.error("collect exception error: " + e.getMessage());
  }
}
