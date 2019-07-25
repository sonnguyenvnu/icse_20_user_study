public UntypedResultSet process(final ConsistencyLevel cl,final ConsistencyLevel serialConsistencyLevel,final String query,Long writetime,final Object... values){
  return process(cl,serialConsistencyLevel,ClientState.forInternalCalls(),query,writetime,values);
}
