public static Account empty(Long id){
  Account account=new Account();
  account.setId(id);
  account.setBorrowLimitAmount(BidConst.DEFALUT_BORROWLIMITAMOUNT);
  account.setRemainBorrowLimit(BidConst.DEFALUT_BORROWLIMITAMOUNT);
  return account;
}
