@Override public Iterable<HistoricItem> query(FilterCriteria filter){
  String json=map.get(filter.getItemName());
  if (json == null) {
    return Collections.emptyList();
  }
  Optional<MapDbItem> item=deserialize(json);
  if (!item.isPresent()) {
    return Collections.emptyList();
  }
  return Collections.singletonList(item.get());
}
