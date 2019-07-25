public Response execute(Request request){
  Response response=null;
  boolean newNode;
  do {
    SimpleRequest routedRequest=new SimpleRequest(request.method(),null,request.path(),request.params(),request.body());
    newNode=false;
    try {
      response=currentTransport.execute(routedRequest);
      ByteSequence body=routedRequest.body();
      if (body != null) {
        stats.bytesSent+=body.length();
      }
    }
 catch (    Exception ex) {
      if (ex instanceof EsHadoopIllegalStateException) {
        throw (EsHadoopException)ex;
      }
      if (ex instanceof javax.net.ssl.SSLException) {
        throw new EsHadoopTransportException(ex);
      }
      if (ex instanceof BindException) {
        throw new EsHadoopTransportException(ex);
      }
      if (log.isTraceEnabled()) {
        log.trace(String.format("Caught exception while performing request [%s][%s] - falling back to the next node in line...",currentNode,request.path()),ex);
      }
      String failed=currentNode;
      failedNodes.put(failed,ex);
      newNode=selectNextNode();
      log.error(String.format("Node [%s] failed (%s); " + (newNode ? "selected next node [" + currentNode + "]" : "no other nodes left - aborting..."),failed,ex.getMessage()));
      if (!newNode) {
        throw new EsHadoopNoNodesLeftException(failedNodes);
      }
    }
  }
 while (newNode);
  return response;
}
