public void run(){
  try {
    log.fine("Processing HTTP request: " + getHttpExchange().getRequestMethod() + " " + getHttpExchange().getRequestURI());
    StreamRequestMessage requestMessage=new StreamRequestMessage(UpnpRequest.Method.getByHttpName(getHttpExchange().getRequestMethod()),getHttpExchange().getRequestURI());
    if (requestMessage.getOperation().getMethod().equals(UpnpRequest.Method.UNKNOWN)) {
      log.fine("Method not supported by UPnP stack: " + getHttpExchange().getRequestMethod());
      throw new RuntimeException("Method not supported: " + getHttpExchange().getRequestMethod());
    }
    requestMessage.getOperation().setHttpMinorVersion(getHttpExchange().getProtocol().toUpperCase(Locale.ROOT).equals("HTTP/1.1") ? 1 : 0);
    log.fine("Created new request message: " + requestMessage);
    requestMessage.setConnection(createConnection());
    requestMessage.setHeaders(new UpnpHeaders(getHttpExchange().getRequestHeaders()));
    byte[] bodyBytes;
    InputStream is=null;
    try {
      is=getHttpExchange().getRequestBody();
      bodyBytes=IO.readBytes(is);
    }
  finally {
      if (is != null)       is.close();
    }
    log.fine("Reading request body bytes: " + bodyBytes.length);
    if (bodyBytes.length > 0 && requestMessage.isContentTypeMissingOrText()) {
      log.fine("Request contains textual entity body, converting then setting string on message");
      requestMessage.setBodyCharacters(bodyBytes);
    }
 else     if (bodyBytes.length > 0) {
      log.fine("Request contains binary entity body, setting bytes on message");
      requestMessage.setBody(UpnpMessage.BodyType.BYTES,bodyBytes);
    }
 else {
      log.fine("Request did not contain entity body");
    }
    StreamResponseMessage responseMessage=process(requestMessage);
    if (responseMessage != null) {
      log.fine("Preparing HTTP response message: " + responseMessage);
      getHttpExchange().getResponseHeaders().putAll(responseMessage.getHeaders());
      byte[] responseBodyBytes=responseMessage.hasBody() ? responseMessage.getBodyBytes() : null;
      int contentLength=responseBodyBytes != null ? responseBodyBytes.length : -1;
      log.fine("Sending HTTP response message: " + responseMessage + " with content length: " + contentLength);
      getHttpExchange().sendResponseHeaders(responseMessage.getOperation().getStatusCode(),contentLength);
      if (contentLength > 0) {
        log.fine("Response message has body, writing bytes to stream...");
        OutputStream os=null;
        try {
          os=getHttpExchange().getResponseBody();
          IO.writeBytes(os,responseBodyBytes);
          os.flush();
        }
  finally {
          if (os != null)           os.close();
        }
      }
    }
 else {
      log.fine("Sending HTTP response status: " + HttpURLConnection.HTTP_NOT_FOUND);
      getHttpExchange().sendResponseHeaders(HttpURLConnection.HTTP_NOT_FOUND,-1);
    }
    responseSent(responseMessage);
  }
 catch (  Throwable t) {
    log.fine("Exception occured during UPnP stream processing: " + t);
    if (log.isLoggable(Level.FINE)) {
      log.log(Level.FINE,"Cause: " + Exceptions.unwrap(t),Exceptions.unwrap(t));
    }
    try {
      httpExchange.sendResponseHeaders(HttpURLConnection.HTTP_INTERNAL_ERROR,-1);
    }
 catch (    IOException ex) {
      log.warning("Couldn't send error response: " + ex);
    }
    responseException(t);
  }
}
