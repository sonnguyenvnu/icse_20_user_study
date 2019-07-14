/** 
 * (0:Email Sent,1:Cancelled 2:Awaiting Approval 3:Rejected 4:Processing 5:Failure 6Completed) 
 */
private static FundingRecord.Status withdrawStatus(int status){
switch (status) {
case 0:
case 2:
case 4:
    return Status.PROCESSING;
case 1:
  return Status.CANCELLED;
case 3:
case 5:
return Status.FAILED;
case 6:
return Status.COMPLETE;
default :
throw new RuntimeException("Unknown binance withdraw status: " + status);
}
}
