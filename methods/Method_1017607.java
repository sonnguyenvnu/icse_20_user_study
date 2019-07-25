public synchronized void fetch(ResultCursor cursor,ResultHandler handler,int fetchSize) throws SQLException {
  waitOnLock();
  final Portal portal=(Portal)cursor;
  final ResultHandler delegateHandler=handler;
  handler=new ResultHandlerDelegate(delegateHandler){
    public void handleCommandStatus(    String status,    int updateCount,    long insertOID){
      handleResultRows(portal.getQuery(),null,new ArrayList<byte[][]>(),null);
    }
  }
;
  try {
    processDeadParsedQueries();
    processDeadPortals();
    sendExecute(portal.getQuery(),portal,fetchSize);
    sendSync();
    processResults(handler,0);
    estimatedReceiveBufferBytes=0;
  }
 catch (  IOException e) {
    abort();
    handler.handleError(new PSQLException(GT.tr("An I/O error occurred while sending to the backend."),PSQLState.CONNECTION_FAILURE,e));
  }
  handler.handleCompletion();
}
