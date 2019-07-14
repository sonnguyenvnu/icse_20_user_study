/** 
 * ??????ID
 * @param workerId
 * @param datacenterId
 * @return
 */
public static long getOrderId(long workerId,long datacenterId){
  SnowflakeIdWorker idWorker=new SnowflakeIdWorker(0,0);
  return idWorker.nextId();
}
