/** 
 * Create aggregation according to the SQL function. zhongshu-comment ??sql?????????agg???sql??count()?sum()?????agg????????agg??eg? select a,b,count(c),sum(d) from tbl group by a,b
 * @param field  SQL function
 * @param parent parentAggregation
 * @return AggregationBuilder represents the SQL function
 * @throws SqlParseException in case of unrecognized function
 */
public AggregationBuilder makeFieldAgg(MethodField field,AggregationBuilder parent) throws SqlParseException {
  groupMap.put(field.getAlias(),new KVValue("FIELD",parent));
  ValuesSourceAggregationBuilder builder;
  field.setAlias(fixAlias(field.getAlias()));
switch (field.getName().toUpperCase()) {
case "SUM":
    builder=AggregationBuilders.sum(field.getAlias());
  return addFieldToAgg(field,builder);
case "MAX":
builder=AggregationBuilders.max(field.getAlias());
return addFieldToAgg(field,builder);
case "MIN":
builder=AggregationBuilders.min(field.getAlias());
return addFieldToAgg(field,builder);
case "AVG":
builder=AggregationBuilders.avg(field.getAlias());
return addFieldToAgg(field,builder);
case "STATS":
builder=AggregationBuilders.stats(field.getAlias());
return addFieldToAgg(field,builder);
case "EXTENDED_STATS":
builder=AggregationBuilders.extendedStats(field.getAlias());
return addFieldToAgg(field,builder);
case "PERCENTILES":
builder=AggregationBuilders.percentiles(field.getAlias());
addSpecificPercentiles((PercentilesAggregationBuilder)builder,field.getParams());
return addFieldToAgg(field,builder);
case "TOPHITS":
return makeTopHitsAgg(field);
case "SCRIPTED_METRIC":
return scriptedMetric(field);
case "COUNT":
groupMap.put(field.getAlias(),new KVValue("COUNT",parent));
return addFieldToAgg(field,makeCountAgg(field));
default :
throw new SqlParseException("the agg function not to define !");
}
}
