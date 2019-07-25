@Override public void update(Session session,Row oldRow,Row newRow){
  if (!rowsAreEqual(oldRow,newRow)) {
    super.update(session,oldRow,newRow);
  }
}
