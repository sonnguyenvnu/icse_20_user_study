private static FundingRecord adapt(TheRockTransaction txn,FundingRecord.Type type){
  TheRockTransaction.TransferDetail transferDetail=txn.getTransferDetail();
  String transferDetailId=null;
  String address=null;
  if (transferDetail != null) {
    transferDetailId=transferDetail.getId();
    address=transferDetail.getRecipient();
  }
  return new FundingRecord(address,txn.getDate(),Currency.getInstance(txn.getCurrency()),txn.getPrice(),String.valueOf(txn.getId()),transferDetailId,type,FundingRecord.Status.COMPLETE,null,null,null);
}
