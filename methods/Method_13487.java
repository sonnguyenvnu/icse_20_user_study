@StreamListener("input4") public void receiveTransactionalMsg(String transactionMsg){
  System.out.println("input4 receive transaction msg: " + transactionMsg);
}
