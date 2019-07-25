@Override public void truncate(Session session){
  TransactionMap<Value,Value> map=getMap(session);
  if (mvTable.getContainsLargeObject()) {
    database.getLobStorage().removeAllForTable(table.getId());
  }
  map.clear();
}
