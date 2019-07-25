@Override public Properties reduce(final String group,final Key key,final Iterator<Properties> iter,final Set<String> groupBy,final ElementAggregator viewAggregator){
  if (!iter.hasNext()) {
    return new Properties();
  }
  final Properties properties=iter.next();
  if (!iter.hasNext()) {
    return properties;
  }
  final ElementAggregator aggregator=schema.getElement(group).getQueryAggregator(groupBy,viewAggregator);
  Properties aggregatedProps=properties;
  while (iter.hasNext()) {
    aggregatedProps=aggregator.apply(aggregatedProps,iter.next());
  }
  return aggregatedProps;
}
