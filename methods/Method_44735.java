public static List<FundingRecord> adaptFundingRecords(PoloniexDepositsWithdrawalsResponse poloFundings){
  final ArrayList<FundingRecord> fundingRecords=new ArrayList<>();
  for (  PoloniexDeposit d : poloFundings.getDeposits()) {
    fundingRecords.add(new FundingRecord(d.getAddress(),d.getTimestamp(),Currency.getInstance(d.getCurrency()),d.getAmount(),String.valueOf(d.getDepositNumber()),d.getTxid(),DEPOSIT,FundingRecord.Status.resolveStatus(d.getStatus()),null,null,d.getStatus()));
  }
  for (  PoloniexWithdrawal w : poloFundings.getWithdrawals()) {
    final String[] statusParts=w.getStatus().split(": *");
    final String statusStr=statusParts[0];
    final FundingRecord.Status status=FundingRecord.Status.resolveStatus(statusStr);
    final String externalId=statusParts.length == 1 ? null : statusParts[1];
    final BigDecimal feeAmount=w.getFee();
    fundingRecords.add(new FundingRecord(w.getAddress(),w.getTimestamp(),Currency.getInstance(w.getCurrency()),w.getAmount(),String.valueOf(w.getWithdrawalNumber()),externalId,WITHDRAWAL,status,null,feeAmount,w.getStatus()));
  }
  return fundingRecords;
}
