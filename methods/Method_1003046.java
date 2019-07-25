@Override public ResultInterface query(int limit){
  setParameters();
  Prepared prepared=procedure.getPrepared();
  return prepared.query(limit);
}
