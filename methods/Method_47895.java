@Override public long getTotalCount(){
  loadRecords();
  return list.getTotalCount();
}
