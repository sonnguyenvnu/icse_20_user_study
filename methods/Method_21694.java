/** 
 * ??????
 * @param field
 * @return
 */
private RangeAggregationBuilder rangeBuilder(MethodField field){
  LinkedList<KVValue> params=field.getParams().stream().filter(kv -> !"alias".equals(kv.key)).collect(Collectors.toCollection(LinkedList::new));
  String fieldName=params.poll().toString();
  double[] ds=Util.KV2DoubleArr(params);
  RangeAggregationBuilder range=AggregationBuilders.range(field.getAlias()).field(fieldName);
  for (int i=1; i < ds.length; i++) {
    range.addRange(ds[i - 1],ds[i]);
  }
  return range;
}
