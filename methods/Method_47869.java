@Nullable @Override public Repetition getNewest(){
  Timestamp newestTimestamp=Timestamp.ZERO;
  Repetition newestRep=null;
  for (  Repetition rep : list) {
    if (rep.getTimestamp().isNewerThan(newestTimestamp)) {
      newestRep=rep;
      newestTimestamp=rep.getTimestamp();
    }
  }
  return newestRep;
}
