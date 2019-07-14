public static FundingRecord adaptFundingRecord(CoinPrepay p,Function<Long,String> currency){
  return new FundingRecord.Builder().setInternalId(Long.toString(p.prepayId)).setAddress(p.addr).setAmount(p.volume).setCurrency(Currency.getInstance(currency.apply(p.coinId))).setType(Type.DEPOSIT).setStatus(convertStatus(p.status)).setBlockchainTransactionHash(p.txId).setDescription(p.tag).setDate(p.getArriveTime()).build();
}
