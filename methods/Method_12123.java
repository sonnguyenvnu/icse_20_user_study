public String getAbstractInfo(){
  return MD5Utils.MD5(usableAmount.add(freezedAmount).add(remainBorrowLimit).toString());
}
