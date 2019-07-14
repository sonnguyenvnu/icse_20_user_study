/** 
 * TOPHITS??
 * @param field
 * @return
 */
private AbstractAggregationBuilder makeTopHitsAgg(MethodField field){
  String alias=gettAggNameFromParamsOrAlias(field);
  TopHitsAggregationBuilder topHits=AggregationBuilders.topHits(alias);
  List<KVValue> params=field.getParams();
  String[] include=null;
  String[] exclude=null;
  for (  KVValue kv : params) {
switch (kv.key) {
case "from":
      topHits.from((int)kv.value);
    break;
case "size":
  topHits.size((int)kv.value);
break;
case "include":
include=kv.value.toString().split(",");
break;
case "exclude":
exclude=kv.value.toString().split(",");
break;
case "alias":
case "nested":
case "reverse_nested":
case "children":
break;
default :
topHits.sort(kv.key,SortOrder.valueOf(kv.value.toString().toUpperCase()));
break;
}
}
if (include != null || exclude != null) {
topHits.fetchSource(include,exclude);
}
return topHits;
}
