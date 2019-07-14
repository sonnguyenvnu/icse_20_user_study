private void addSpecificPercentiles(PercentilesAggregationBuilder percentilesBuilder,List<KVValue> params){
  List<Double> percentiles=new ArrayList<>();
  for (  KVValue kValue : params) {
    if (kValue.value.getClass().equals(BigDecimal.class)) {
      BigDecimal percentile=(BigDecimal)kValue.value;
      percentiles.add(percentile.doubleValue());
    }
 else     if (kValue.value instanceof Integer) {
      percentiles.add(((Integer)kValue.value).doubleValue());
    }
  }
  if (percentiles.size() > 0) {
    double[] percentilesArr=new double[percentiles.size()];
    int i=0;
    for (    Double percentile : percentiles) {
      percentilesArr[i]=percentile;
      i++;
    }
    percentilesBuilder.percentiles(percentilesArr);
  }
}
