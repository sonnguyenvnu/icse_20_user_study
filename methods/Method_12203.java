@Override @Compensable(confirmMethod="confirmIsNotSold",cancelMethod="cancelIsNotSold",transactionContextEditor=DubboTransactionContextEditor.class) public boolean isNotSold(String seats){
  if (seats.equals("4,5")) {
    throw new IllegalArgumentException();
  }
 else {
    return true;
  }
}
