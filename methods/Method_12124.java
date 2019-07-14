public boolean checkAbstractInfo(){
  return MD5Utils.MD5(usableAmount.add(freezedAmount).add(remainBorrowLimit).toString()).equals(abstractInfo);
}
