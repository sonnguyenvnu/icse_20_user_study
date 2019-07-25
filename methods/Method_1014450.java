private Optional<MapDbItem> deserialize(String json){
  MapDbItem item=mapper.<MapDbItem>fromJson(json,MapDbItem.class);
  if (item == null || !item.isValid()) {
    logger.warn("Deserialized invalid item: {}",item);
    return Optional.empty();
  }
  return Optional.of(item);
}
