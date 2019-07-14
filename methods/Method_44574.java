public List<UserTrade> tradeHistory(Date startTime,Date endTime,Integer limit,Long offset) throws IOException {
  long end=DateUtils.toMillisNullSafe(endTime);
  long start=Math.max(DateUtils.toMillisNullSafe(startTime),end - THIRTY_DAYS_MILLISECONDS);
  List<Map> response=service.transactions(apiKey,signatureCreator,String.valueOf(start),String.valueOf(end),"BUY,SELL",limit,offset);
  List<UserTrade> resp=new ArrayList<>();
  for (  Map map : response) {
    UserTrade fundingRecord=LivecoinAdapters.adaptUserTrade(map);
    resp.add(fundingRecord);
  }
  return resp;
}
