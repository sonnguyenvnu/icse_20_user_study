public static List<FundingRecord> adaptDepositsWithdrawals(GateioDepositsWithdrawals depositsWithdrawals){
  List<FundingRecord> result=new ArrayList<>();
  depositsWithdrawals.getDeposits().forEach(d -> {
    FundingRecord r=new FundingRecord(d.address,d.getTimestamp(),Currency.getInstance(d.currency),d.amount,d.id,d.txid,FundingRecord.Type.DEPOSIT,status(d.status),null,null,null);
    result.add(r);
  }
);
  depositsWithdrawals.getWithdraws().forEach(w -> {
    FundingRecord r=new FundingRecord(w.address,w.getTimestamp(),Currency.getInstance(w.currency),w.amount,w.id,w.txid,FundingRecord.Type.WITHDRAWAL,status(w.status),null,null,null);
    result.add(r);
  }
);
  return result;
}
