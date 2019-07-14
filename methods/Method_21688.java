private AggregationBuilder geoBounds(MethodField field) throws SqlParseException {
  String aggName=gettAggNameFromParamsOrAlias(field);
  GeoBoundsAggregationBuilder boundsBuilder=AggregationBuilders.geoBounds(aggName);
  String value=null;
  for (  KVValue kv : field.getParams()) {
    value=kv.value.toString();
switch (kv.key.toLowerCase()) {
case "field":
      boundsBuilder.field(value);
    break;
case "wrap_longitude":
  boundsBuilder.wrapLongitude(Boolean.getBoolean(value));
break;
case "alias":
case "nested":
case "reverse_nested":
case "children":
break;
default :
throw new SqlParseException("geo_bounds err or not define field " + kv.toString());
}
}
return boundsBuilder;
}
