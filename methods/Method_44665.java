/** 
 * The status of deposits (0: waiting for confirmation; 1: confirmation account; 2: recharge success);
 */
private static Status convertDepositStatus(String status){
switch (status) {
case "0":
case "1":
    return Status.PROCESSING;
case "2":
  return Status.COMPLETE;
default :
throw new ExchangeException("Unknown withdrawal status: " + status);
}
}
