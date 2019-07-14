/** 
 * (0:pending,1:success) 
 */
private static FundingRecord.Status depositStatus(int status){
switch (status) {
case 0:
    return Status.PROCESSING;
case 1:
  return Status.COMPLETE;
default :
throw new RuntimeException("Unknown binance deposit status: " + status);
}
}
