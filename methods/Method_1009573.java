@Override public void run(){
  try {
    StreamRequestMessage requestMessage=readRequestMessage();
    if (log.isLoggable(Level.FINER))     log.finer("Processing new request message: " + requestMessage);
    responseMessage=process(requestMessage);
    if (responseMessage != null) {
      if (log.isLoggable(Level.FINER))       log.finer("Preparing HTTP response message: " + responseMessage);
      writeResponseMessage(responseMessage);
    }
 else {
      if (log.isLoggable(Level.FINER))       log.finer("Sending HTTP response status: " + HttpURLConnection.HTTP_NOT_FOUND);
      getResponse().setStatus(HttpServletResponse.SC_NOT_FOUND);
    }
  }
 catch (  Throwable t) {
    log.info("Exception occurred during UPnP stream processing: " + t);
    if (log.isLoggable(Level.FINER)) {
      log.log(Level.FINER,"Cause: " + Exceptions.unwrap(t),Exceptions.unwrap(t));
    }
    if (!getResponse().isCommitted()) {
      log.finer("Response hasn't been committed, returning INTERNAL SERVER ERROR to client");
      getResponse().setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }
 else {
      log.info("Could not return INTERNAL SERVER ERROR to client, response was already committed");
    }
    responseException(t);
  }
 finally {
    complete();
  }
}
