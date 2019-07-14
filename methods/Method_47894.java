@Override @Nullable public Repetition getByTimestamp(Timestamp timestamp){
  loadRecords();
  return list.getByTimestamp(timestamp);
}
