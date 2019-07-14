public List<WithdrawList.BinanceWithdraw> withdrawHistory(String asset,Long startTime,Long endTime,Long recvWindow,long timestamp) throws BinanceException, IOException {
  WithdrawList result=binance.withdrawHistory(asset,startTime,endTime,recvWindow,timestamp,super.apiKey,super.signatureCreator);
  return checkWapiResponse(result);
}
