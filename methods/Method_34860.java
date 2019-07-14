/** 
 * @see HttpServlet#doGet(HttpServletRequest request,HttpServletResponse response)
 */
protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
  String origin=request.getParameter("origin");
  String authorization=request.getParameter("authorization");
  if (origin == null) {
    response.setStatus(500);
    response.getWriter().println("Required parameter 'origin' missing. Example: 107.20.175.135:7001");
    return;
  }
  origin=origin.trim();
  HttpGet httpget=null;
  InputStream is=null;
  boolean hasFirstParameter=false;
  StringBuilder url=new StringBuilder();
  if (!origin.startsWith("http")) {
    url.append("http://");
  }
  url.append(origin);
  if (origin.contains("?")) {
    hasFirstParameter=true;
  }
  @SuppressWarnings("unchecked") Map<String,String[]> params=request.getParameterMap();
  for (  String key : params.keySet()) {
    if (!key.equals("origin") && !key.equals("authorization")) {
      String[] values=params.get(key);
      String value=values[0].trim();
      if (hasFirstParameter) {
        url.append("&");
      }
 else {
        url.append("?");
        hasFirstParameter=true;
      }
      url.append(key).append("=").append(value);
    }
  }
  String proxyUrl=url.toString();
  logger.info("\n\nProxy opening connection to: {}\n\n",proxyUrl);
  try {
    httpget=new HttpGet(proxyUrl);
    if (authorization != null) {
      httpget.addHeader("Authorization",authorization);
    }
    HttpClient client=ProxyConnectionManager.httpClient;
    HttpResponse httpResponse=client.execute(httpget);
    int statusCode=httpResponse.getStatusLine().getStatusCode();
    if (statusCode == HttpStatus.SC_OK) {
      is=httpResponse.getEntity().getContent();
      for (      Header header : httpResponse.getAllHeaders()) {
        if (!HttpHeaders.TRANSFER_ENCODING.equals(header.getName())) {
          response.addHeader(header.getName(),header.getValue());
        }
      }
      OutputStream os=response.getOutputStream();
      int b=-1;
      while ((b=is.read()) != -1) {
        try {
          os.write(b);
          if (b == 10) {
            os.flush();
          }
        }
 catch (        Exception e) {
          if (e.getClass().getSimpleName().equalsIgnoreCase("ClientAbortException")) {
            logger.debug("Connection closed by client. Will stop proxying ...");
            break;
          }
 else {
            throw new RuntimeException(e);
          }
        }
      }
    }
  }
 catch (  Exception e) {
    logger.error("Error proxying request: " + url,e);
  }
 finally {
    if (httpget != null) {
      try {
        httpget.abort();
      }
 catch (      Exception e) {
        logger.error("failed aborting proxy connection.",e);
      }
    }
    if (is != null) {
      try {
        is.close();
      }
 catch (      Exception e) {
      }
    }
  }
}
