public UntypedResultSet process(final ConsistencyLevel cl,final ConsistencyLevel serialConsistencyLevel,ClientState clientState,final String query,Long writetime,final Object... values) throws RequestExecutionException, RequestValidationException, InvalidRequestException {
  if (logger.isDebugEnabled())   logger.debug("processing CL={} SERIAL_CL={} query={} values={}",cl,serialConsistencyLevel,query,Arrays.asList(values));
  QueryState queryState=new QueryState(clientState);
  ResultMessage.Prepared prepared=ClientState.getCQLQueryHandler().prepare(query,queryState,Collections.EMPTY_MAP);
  List<ByteBuffer> boundValues=new ArrayList<ByteBuffer>(values.length);
  for (int i=0; i < values.length; i++) {
    Object v=values[i];
    AbstractType type=prepared.metadata.names.get(i).type;
    boundValues.add(v instanceof ByteBuffer || v == null ? (ByteBuffer)v : type.decompose(v));
  }
  QueryOptions queryOptions=(serialConsistencyLevel == null) ? QueryOptions.forInternalCalls(cl,boundValues) : QueryOptions.forInternalCalls(cl,serialConsistencyLevel,boundValues);
  ResultMessage result=ClientState.getCQLQueryHandler().process(query,queryState,queryOptions,Collections.EMPTY_MAP,System.nanoTime());
  writetime=queryState.getTimestamp();
  return (result instanceof ResultMessage.Rows) ? UntypedResultSet.create(((ResultMessage.Rows)result).result) : null;
}
