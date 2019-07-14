@Override public List<Repetition> getByInterval(Timestamp timeFrom,Timestamp timeTo){
  loadRecords();
  return list.getByInterval(timeFrom,timeTo);
}
