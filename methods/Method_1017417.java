@Override public HttpResponse execute(HttpRequest request) throws HttpClientException {
  HttpResponse response=null;
  try {
    HttpURLConnection connection=createHttpConnection(request);
    String method=request.getMethod().name();
    connection.setRequestMethod(method);
    connection.setDoInput(true);
    connection.setInstanceFollowRedirects("GET".equals(method));
    if ("PUT".equals(method) || "POST".equals(method) || "PATCH".equals(method) || "DELETE".equals(method)) {
      connection.setDoOutput(true);
    }
 else {
      connection.setDoOutput(false);
    }
    HttpHeaders headers=request.getHeaders();
    if (headers == null) {
      headers=new HttpHeaders();
    }
    if (!headers.containsKey(HttpHeaders.HOST)) {
      headers.set(HttpHeaders.HOST,request.getURI().getHost());
    }
    if (!headers.containsKey(HttpHeaders.ACCEPT)) {
      headers.set(HttpHeaders.ACCEPT,"*/*");
    }
    if (!headers.containsKey(HttpHeaders.USER_AGENT)) {
      headers.set(HttpHeaders.USER_AGENT,"jdk/httpclient");
    }
    for (    Entry<String,List<String>> header : headers.entrySet()) {
      if (HttpHeaders.COOKIE.equalsIgnoreCase(header.getKey())) {
        connection.setRequestProperty(header.getKey(),StringUtil.join(header.getValue(),';'));
      }
 else {
        for (        String headerValue : header.getValue()) {
          connection.addRequestProperty(header.getKey(),headerValue != null ? headerValue : "");
        }
      }
    }
    HttpEntity httpEntity=request.getEntity();
    if (httpEntity != null) {
      connection.setUseCaches(false);
      if (httpEntity.getContentLength() > 0l) {
        connection.setRequestProperty(HttpHeaders.CONTENT_LENGTH,Long.toString(httpEntity.getContentLength()));
      }
      if (httpEntity.getContentType() != null) {
        connection.setRequestProperty(HttpHeaders.CONTENT_TYPE,httpEntity.getContentType().toString());
      }
    }
    connection.connect();
    if (httpEntity != null) {
      OutputStream output=connection.getOutputStream();
      httpEntity.writeTo(output);
      output.flush();
      output.close();
    }
    InputStream input=connection.getErrorStream() != null ? connection.getErrorStream() : connection.getInputStream();
    byte[] content=IOUtil.toByteArray(input);
    response=new SimpleHttpResponse(connection,content);
    input.close();
    handleResponse(response);
  }
 catch (  IOException e) {
    throw new HttpClientException("I/O error on " + request.getMethod().name() + " request for \"" + request.getURI().toString(),e);
  }
 finally {
    if (response != null) {
      response.close();
    }
  }
  return response;
}
