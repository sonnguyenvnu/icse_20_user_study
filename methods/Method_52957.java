private void ensureResponseEvaluated(){
  if (th != null) {
    throw new TwitterRuntimeException(th);
  }
  if (responseGot) {
    return;
  }
  responseGot=true;
  if (future.isCancelled()) {
    th=new TwitterException("HttpResponse already disconnected.");
    throw new TwitterRuntimeException(th);
  }
  try {
    HTTPResponse r=future.get();
    statusCode=r.getResponseCode();
    headers=new HashMap<String,String>();
    for (    HTTPHeader h : r.getHeaders()) {
      headers.put(h.getName().toLowerCase(Locale.ENGLISH),h.getValue());
    }
    byte[] content=r.getContent();
    is=new ByteArrayInputStream(content);
    if ("gzip".equals(headers.get("content-encoding"))) {
      try {
        is=new GZIPInputStream(is);
      }
 catch (      IOException e) {
        th=e;
        throw new TwitterRuntimeException(th);
      }
    }
    responseAsString=inputStreamToString(is);
    if (statusCode < OK || (statusCode != FOUND && MULTIPLE_CHOICES <= statusCode)) {
      if (statusCode == ENHANCE_YOUR_CLAIM || statusCode == BAD_REQUEST || statusCode < INTERNAL_SERVER_ERROR) {
        th=new TwitterException(responseAsString,null,statusCode);
        throw new TwitterRuntimeException(th);
      }
    }
  }
 catch (  ExecutionException e) {
    th=e.getCause();
  }
catch (  InterruptedException e) {
    th=e.getCause();
  }
  if (th != null) {
    throw new TwitterRuntimeException(th);
  }
}
