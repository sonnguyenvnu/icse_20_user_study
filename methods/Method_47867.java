@Nullable @Override public Repetition getByTimestamp(Timestamp timestamp){
  for (  Repetition r : list)   if (r.getTimestamp().equals(timestamp))   return r;
  return null;
}
