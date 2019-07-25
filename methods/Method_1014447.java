@Override public void store(Item item,String alias){
  if (item.getState() instanceof UnDefType) {
    return;
  }
  if (alias == null) {
    alias=item.getName();
  }
  logger.debug("store called for {}",alias);
  State state=item.getState();
  MapDbItem mItem=new MapDbItem();
  mItem.setName(alias);
  mItem.setState(state);
  mItem.setTimestamp(new Date());
  String json=serialize(mItem);
  map.put(alias,json);
  commit();
  logger.debug("Stored '{}' with state '{}' in MapDB database",alias,state.toString());
}
