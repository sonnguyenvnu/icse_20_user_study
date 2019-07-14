@NonNull @Override public List<Score> getByInterval(@NonNull Timestamp fromTimestamp,@NonNull Timestamp toTimestamp){
  compute(fromTimestamp,toTimestamp);
  List<Score> filtered=new LinkedList<>();
  for (  Score s : list) {
    if (s.getTimestamp().isNewerThan(toTimestamp) || s.getTimestamp().isOlderThan(fromTimestamp))     continue;
    filtered.add(s);
  }
  return filtered;
}
