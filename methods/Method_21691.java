private ValuesSourceAggregationBuilder dateRange(MethodField field){
  String alias=gettAggNameFromParamsOrAlias(field);
  DateRangeAggregationBuilder dateRange=AggregationBuilders.dateRange(alias).format(TIME_FARMAT);
  String value=null;
  List<String> ranges=new ArrayList<>();
  for (  KVValue kv : field.getParams()) {
    value=kv.value.toString();
    if ("field".equals(kv.key)) {
      dateRange.field(value);
      continue;
    }
 else     if ("format".equals(kv.key)) {
      dateRange.format(value);
      continue;
    }
 else     if ("time_zone".equals(kv.key)) {
      dateRange.timeZone(DateTimeZone.forTimeZone(TimeZone.getTimeZone(ZoneOffset.of(value))));
      continue;
    }
 else     if ("from".equals(kv.key)) {
      dateRange.addUnboundedFrom(kv.value.toString());
      continue;
    }
 else     if ("to".equals(kv.key)) {
      dateRange.addUnboundedTo(kv.value.toString());
      continue;
    }
 else     if ("alias".equals(kv.key) || "nested".equals(kv.key) || "children".equals(kv.key)) {
      continue;
    }
 else {
      ranges.add(value);
    }
  }
  for (int i=1; i < ranges.size(); i++) {
    dateRange.addRange(ranges.get(i - 1),ranges.get(i));
  }
  return dateRange;
}
