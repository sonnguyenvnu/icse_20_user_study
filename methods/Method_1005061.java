@Override public Value reduce(final Key key,final Iterator<Value> iter){
  Value value=iter.next();
  if (!iter.hasNext()) {
    return value;
  }
  final String group=elementConverter.getGroupFromColumnFamily(key.getColumnFamilyData().getBackingArray());
  Properties properties;
  final ElementAggregator aggregator=schema.getElement(group).getIngestAggregator();
  try {
    properties=elementConverter.getPropertiesFromValue(group,value);
  }
 catch (  final AccumuloElementConversionException e) {
    throw new AggregationException("Failed to recreate a graph element from a key and value",e);
  }
  Properties aggregatedProps=properties;
  while (iter.hasNext()) {
    value=iter.next();
    try {
      properties=elementConverter.getPropertiesFromValue(group,value);
    }
 catch (    final AccumuloElementConversionException e) {
      throw new AggregationException("Failed to recreate a graph element from a key and value",e);
    }
    aggregatedProps=aggregator.apply(aggregatedProps,properties);
  }
  try {
    return elementConverter.getValueFromProperties(group,aggregatedProps);
  }
 catch (  final AccumuloElementConversionException e) {
    throw new AggregationException("Failed to create an accumulo value from an elements properties",e);
  }
}
