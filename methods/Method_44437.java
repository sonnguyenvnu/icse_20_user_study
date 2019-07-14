protected static FeeTier[] adaptFeeTiers(List<KrakenFee> makerFees,List<KrakenFee> takerFees){
  Collections.sort(makerFees);
  Collections.sort(takerFees);
  List<FeeTier> resultFeeTiers=new ArrayList<FeeTier>();
  int makerFeeIdx=0;
  int takerFeeIdx=0;
  while (makerFeeIdx < makerFees.size() || takerFeeIdx < takerFees.size()) {
    int curMakerIdx=Math.min(makerFeeIdx,makerFees.size() - 1);
    int curTakerIdx=Math.min(takerFeeIdx,takerFees.size() - 1);
    BigDecimal quantityMaker=makerFees.get(curMakerIdx).getVolume();
    BigDecimal quantityTaker=takerFees.get(curTakerIdx).getVolume();
    BigDecimal resultQuantity=null;
    BigDecimal resultMakerFee=null;
    BigDecimal resultTakerFee=null;
    int makerVolCompTakerVol=quantityMaker.compareTo(quantityTaker);
    if ((makerVolCompTakerVol > 0 || makerFeeIdx >= makerFees.size()) && takerFeeIdx < takerFees.size()) {
      if (makerFeeIdx < 1) {
        throw new IllegalStateException("Kraken exchange specified fee tiers such that the maker fee was unspecified before a nonzero quantity was traded.");
      }
      KrakenFee takerFeeData=takerFees.get(curTakerIdx);
      resultTakerFee=takerFeeData.getPercentFee();
      resultMakerFee=makerFees.get(makerFeeIdx - 1).getPercentFee();
      resultQuantity=takerFeeData.getVolume();
      takerFeeIdx++;
    }
 else     if ((makerVolCompTakerVol < 0 || takerFeeIdx >= takerFees.size()) && makerFeeIdx < makerFees.size()) {
      if (takerFeeIdx < 1) {
        throw new IllegalStateException("Kraken exchange specified fee tiers such that the taker fee was unspecified before a nonzero quantity was traded.");
      }
      KrakenFee makerFeeData=makerFees.get(curMakerIdx);
      resultMakerFee=makerFeeData.getPercentFee();
      resultTakerFee=takerFees.get(takerFeeIdx - 1).getPercentFee();
      resultQuantity=makerFeeData.getVolume();
      makerFeeIdx++;
    }
 else {
      KrakenFee makerFeeData=makerFees.get(curMakerIdx);
      resultMakerFee=makerFeeData.getPercentFee();
      resultTakerFee=takerFees.get(curTakerIdx).getPercentFee();
      resultQuantity=makerFeeData.getVolume();
      takerFeeIdx++;
      makerFeeIdx++;
    }
    resultFeeTiers.add(new FeeTier(resultQuantity,new Fee(resultMakerFee.movePointLeft(2),resultTakerFee.movePointLeft(2))));
  }
  return resultFeeTiers.toArray(new FeeTier[resultFeeTiers.size()]);
}
