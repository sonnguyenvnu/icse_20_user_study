private void cullNotWanted(List<FundingRecord> some,BitflyerTradeHistoryParams param){
  if (param != null && param.getCurrencies() != null) {
    Iterator<FundingRecord> iter=some.iterator();
    while (iter.hasNext()) {
      FundingRecord record=iter.next();
      if (!isIn(record.getCurrency(),param.getCurrencies()))       iter.remove();
    }
  }
}
