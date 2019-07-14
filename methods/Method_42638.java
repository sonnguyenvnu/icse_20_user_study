/** 
 * ??Http??
 * @param request ????
 * @param strParaFileName ????????
 * @param strFilePath ????
 * @return 
 * @throws HttpException , IOException 
 */
public HttpResponse execute(HttpRequest request,String strParaFileName,String strFilePath) throws HttpException, IOException {
  HttpClient httpclient=new HttpClient(connectionManager);
  int connectionTimeout=defaultConnectionTimeout;
  if (request.getConnectionTimeout() > 0) {
    connectionTimeout=request.getConnectionTimeout();
  }
  httpclient.getHttpConnectionManager().getParams().setConnectionTimeout(connectionTimeout);
  int soTimeout=defaultSoTimeout;
  if (request.getTimeout() > 0) {
    soTimeout=request.getTimeout();
  }
  httpclient.getHttpConnectionManager().getParams().setSoTimeout(soTimeout);
  httpclient.getParams().setConnectionManagerTimeout(defaultHttpConnectionManagerTimeout);
  String charset=request.getCharset();
  charset=charset == null ? DEFAULT_CHARSET : charset;
  HttpMethod method=null;
  if (request.getMethod().equals(HttpRequest.METHOD_GET)) {
    method=new GetMethod(request.getUrl());
    method.getParams().setCredentialCharset(charset);
    method.setQueryString(request.getQueryString());
  }
 else   if (strParaFileName.equals("") && strFilePath.equals("")) {
    method=new PostMethod(request.getUrl());
    ((PostMethod)method).addParameters(request.getParameters());
    method.addRequestHeader("Content-Type","application/x-www-form-urlencoded; text/html; charset=" + charset);
  }
 else {
    method=new PostMethod(request.getUrl());
    List<Part> parts=new ArrayList<Part>();
    for (int i=0; i < request.getParameters().length; i++) {
      parts.add(new StringPart(request.getParameters()[i].getName(),request.getParameters()[i].getValue(),charset));
    }
    parts.add(new FilePart(strParaFileName,new FilePartSource(new File(strFilePath))));
    ((PostMethod)method).setRequestEntity(new MultipartRequestEntity(parts.toArray(new Part[0]),new HttpMethodParams()));
  }
  method.addRequestHeader("User-Agent","Mozilla/4.0");
  HttpResponse response=new HttpResponse();
  try {
    httpclient.executeMethod(method);
    if (request.getResultType().equals(HttpResultType.STRING)) {
      response.setStringResult(method.getResponseBodyAsString());
    }
 else     if (request.getResultType().equals(HttpResultType.BYTES)) {
      response.setByteResult(method.getResponseBody());
    }
    response.setResponseHeaders(method.getResponseHeaders());
  }
 catch (  UnknownHostException ex) {
    return null;
  }
catch (  IOException ex) {
    return null;
  }
catch (  Exception ex) {
    return null;
  }
 finally {
    method.releaseConnection();
  }
  return response;
}
