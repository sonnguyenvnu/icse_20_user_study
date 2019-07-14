/** 
 * @param accessKeyId accessKeyId
 * @param accessKeySecret accessKeySecret
 * @param messageType ????
 * @param queueName ????
 * @param messageListener ???listener,??????
 * @throws com.aliyuncs.exceptions.ClientException
 * @throws ParseException
 */
public void startReceiveMsg(String accessKeyId,String accessKeySecret,String messageType,String queueName,MessageListener messageListener) throws com.aliyuncs.exceptions.ClientException, ParseException {
  tokenGetter=new TokenGetterForAlicom(accessKeyId,accessKeySecret,endpointNameForPop,regionIdForPop,domainForPop,null);
  this.messageListener=messageListener;
  isRunning=true;
  PullMessageTask task=new PullMessageTask();
  task.messageType=messageType;
  task.queueName=queueName;
synchronized (S_LOCK_OBJ_MAP) {
    lockObj=S_LOCK_OBJ_MAP.get(queueName);
    if (lockObj == null) {
      lockObj=new Object();
      S_LOCK_OBJ_MAP.put(queueName,lockObj);
    }
  }
  if (executorService == null) {
    ScheduledExecutorService scheduledExecutorService=new ScheduledThreadPoolExecutor(pullMsgThreadSize,new BasicThreadFactory.Builder().namingPattern("PullMessageTask-" + messageType + "-thread-pool-%d").daemon(true).build());
    executorService=scheduledExecutorService;
  }
  for (int i=0; i < pullMsgThreadSize; i++) {
    executorService.execute(task);
  }
}
