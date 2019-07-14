@NonNull @Override public synchronized List<Checkmark> getByInterval(Timestamp from,Timestamp to){
  compute();
  Timestamp newestComputed=new Timestamp(0);
  Timestamp oldestComputed=new Timestamp(0).plus(1000000);
  Checkmark newest=getNewestComputed();
  Checkmark oldest=getOldestComputed();
  if (newest != null)   newestComputed=newest.getTimestamp();
  if (oldest != null)   oldestComputed=oldest.getTimestamp();
  List<Checkmark> filtered=new ArrayList<>(Math.max(0,oldestComputed.daysUntil(newestComputed) + 1));
  for (int i=0; i <= from.daysUntil(to); i++) {
    Timestamp t=to.minus(i);
    if (t.isNewerThan(newestComputed) || t.isOlderThan(oldestComputed))     filtered.add(new Checkmark(t,Checkmark.UNCHECKED));
 else     filtered.add(list.get(t.daysUntil(newestComputed)));
  }
  return filtered;
}
