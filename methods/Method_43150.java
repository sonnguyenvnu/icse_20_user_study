public static FundingRecord adaptFundingRecord(BitflyerDepositOrWithdrawal history,FundingRecord.Type type){
  return new FundingRecord.Builder().setDate(BitflyerUtils.parseDate(history.getEventDate())).setCurrency(new Currency(history.getCurrencyCode())).setAmount(history.getAmount()).setInternalId(history.getID()).setType(type).setStatus(adaptStatus(history.getStatus())).setBalance(history.getAmount()).build();
}
