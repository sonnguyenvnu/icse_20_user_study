private boolean sendBatch(int batchSize){
  try {
    AbstractInputStreamContent content=ByteArrayContent.fromString("application/octet-stream",sbBatch.toString());
    Long count=FusionTableHandler.insertRows(service,tableId,content);
    if (count != null && count.intValue() != batchSize) {
      exceptions.add(new IOException("only imported " + count + " of " + batchSize + " rows"));
    }
  }
 catch (  IOException e) {
    exceptions.add(e);
    if (e instanceof HttpResponseException) {
      int code=((HttpResponseException)e).getStatusCode();
      if (code >= 400 && code < 500) {
        return false;
      }
    }
  }
 finally {
    sbBatch=null;
  }
  return true;
}
