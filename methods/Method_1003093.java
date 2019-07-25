@Override public void update(Session session,Row oldRow,Row newRow){
  remove(session,oldRow);
  add(session,newRow);
}
