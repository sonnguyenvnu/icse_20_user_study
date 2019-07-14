private AggregationBuilder geohashGrid(MethodField field) throws SqlParseException {
  String aggName=gettAggNameFromParamsOrAlias(field);
  GeoGridAggregationBuilder geoHashGrid=AggregationBuilders.geohashGrid(aggName);
  String value=null;
  for (  KVValue kv : field.getParams()) {
    value=kv.value.toString();
switch (kv.key.toLowerCase()) {
case "precision":
      geoHashGrid.precision(Integer.parseInt(value));
    break;
case "field":
  geoHashGrid.field(value);
break;
case "size":
geoHashGrid.size(Integer.parseInt(value));
break;
case "shard_size":
geoHashGrid.shardSize(Integer.parseInt(value));
break;
case "alias":
case "nested":
case "reverse_nested":
case "children":
break;
default :
throw new SqlParseException("geohash grid err or not define field " + kv.toString());
}
}
return geoHashGrid;
}
