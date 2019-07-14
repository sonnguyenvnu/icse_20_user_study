@Nullable @Override public Repetition getOldest(){
  Timestamp oldestTimestamp=Timestamp.ZERO.plus(1000000);
  Repetition oldestRep=null;
  for (  Repetition rep : list) {
    if (rep.getTimestamp().isOlderThan(oldestTimestamp)) {
      oldestRep=rep;
      oldestTimestamp=rep.getTimestamp();
    }
  }
  return oldestRep;
}
