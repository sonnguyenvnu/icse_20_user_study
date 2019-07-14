/** 
 * -3:pending cancel; -2: cancelled; -1: failed; 0 :pending; 1 :sending; 2:sent; 3 :email confirmation; 4 :manual confirmation; 5:awaiting identity confirmation
 */
private static Status convertWithdrawalStatus(String status){
switch (status) {
case "-3":
case "-2":
    return Status.CANCELLED;
case "-1":
  return Status.FAILED;
case "0":
case "1":
case "3":
case "4":
case "5":
return Status.PROCESSING;
case "2":
return Status.COMPLETE;
default :
throw new ExchangeException("Unknown withdrawal status: " + status);
}
}
