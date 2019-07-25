@Override public int update(){
  session.removeProcedure(procedureName);
  return 0;
}
