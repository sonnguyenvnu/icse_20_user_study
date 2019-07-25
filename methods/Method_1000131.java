public void merge(){
  if (activeSession <= 0) {
    throw new RevokingStoreIllegalStateException("activeDialog has to be greater than 0");
  }
  if (size < 2) {
    return;
  }
  dbs.forEach(db -> db.getHead().getPrevious().merge(db.getHead()));
  retreat();
  --activeSession;
}
