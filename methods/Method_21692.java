/** 
 * ????????
 * @param field
 * @return
 * @throws SqlParseException
 */
private DateHistogramAggregationBuilder dateHistogram(MethodField field) throws SqlParseException {
  String alias=gettAggNameFromParamsOrAlias(field);
  DateHistogramAggregationBuilder dateHistogram=AggregationBuilders.dateHistogram(alias).format(TIME_FARMAT);
  String value=null;
  for (  KVValue kv : field.getParams()) {
    if (kv.value.toString().contains("doc[")) {
      String script=kv.value + "; return " + kv.key;
      dateHistogram.script(new Script(script));
    }
 else {
      value=kv.value.toString();
switch (kv.key.toLowerCase()) {
case "interval":
        dateHistogram.dateHistogramInterval(new DateHistogramInterval(kv.value.toString()));
      break;
case "field":
    dateHistogram.field(value);
  break;
case "format":
dateHistogram.format(value);
break;
case "time_zone":
dateHistogram.timeZone(DateTimeZone.forTimeZone(TimeZone.getTimeZone(ZoneOffset.of(value))));
break;
case "min_doc_count":
dateHistogram.minDocCount(Long.parseLong(value));
break;
case "order":
dateHistogram.order("desc".equalsIgnoreCase(value) ? BucketOrder.key(false) : BucketOrder.key(true));
break;
case "extended_bounds":
ExtendedBounds extendedBounds=null;
try (XContentParser parser=JsonXContent.jsonXContent.createParser(NamedXContentRegistry.EMPTY,LoggingDeprecationHandler.INSTANCE,value)){
extendedBounds=ExtendedBounds.PARSER.parse(parser,null);
}
 catch (IOException ex) {
List<Integer> indexList=new LinkedList<>();
int index=-1;
while ((index=value.indexOf(':',index + 1)) != -1) {
indexList.add(index);
}
if (!indexList.isEmpty()) {
index=indexList.get(indexList.size() / 2);
extendedBounds=new ExtendedBounds(value.substring(0,index),value.substring(index + 1));
}
}
if (extendedBounds != null) {
dateHistogram.extendedBounds(extendedBounds);
}
break;
case "offset":
dateHistogram.offset(value);
break;
case "alias":
case "nested":
case "reverse_nested":
case "children":
break;
default :
throw new SqlParseException("date range err or not define field " + kv.toString());
}
}
}
return dateHistogram;
}
