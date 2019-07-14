@Override @Compensable(confirmMethod="confirmIsTrueSeats",cancelMethod="cancelIsTrueSeats",transactionContextEditor=DubboTransactionContextEditor.class) public boolean isTrueSeats(String seats){
  if (seats.equals("1,2,3")) {
    throw new IllegalArgumentException();
  }
 else {
    return true;
  }
}
