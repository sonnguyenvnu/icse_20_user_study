private static Status convertStatus(String status){
  if (status == null) {
    return null;
  }
switch (status) {
case "WALLET_PROCESSING":
case "PROCESSING":
    return Status.PROCESSING;
case "SUCCESS":
  return Status.COMPLETE;
case "FAILURE":
return Status.FAILED;
default :
throw new ExchangeException("Not supported status: " + status);
}
}
