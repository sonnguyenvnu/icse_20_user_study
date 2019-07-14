/** 
 * Execute an MMS HTTP request, either a POST (sending) or a GET (downloading)
 * @param urlString The request URL, for sending it is usually the MMSC, and for downloadingit is the message URL
 * @param pdu For POST (sending) only, the PDU to send
 * @param method HTTP method, POST for sending and GET for downloading
 * @param isProxySet Is there a proxy for the MMSC
 * @param proxyHost The proxy host
 * @param proxyPort The proxy port
 * @param mmsConfig The MMS config to use
 * @return The HTTP response body
 * @throws MmsHttpException For any failures
 */
public byte[] execute(String urlString,byte[] pdu,String method,boolean isProxySet,String proxyHost,int proxyPort,MmsConfig.Overridden mmsConfig) throws MmsHttpException {
  Timber.d("HTTP: " + method + " " + urlString + (isProxySet ? (", proxy=" + proxyHost + ":" + proxyPort) : "") + ", PDU size=" + (pdu != null ? pdu.length : 0));
  checkMethod(method);
  HttpURLConnection connection=null;
  try {
    Proxy proxy=null;
    if (isProxySet) {
      proxy=new Proxy(Proxy.Type.HTTP,new InetSocketAddress(proxyHost,proxyPort));
    }
    final URL url=new URL(urlString);
    connection=openConnection(url,proxy);
    connection.setDoInput(true);
    connection.setConnectTimeout(mmsConfig.getHttpSocketTimeout());
    connection.setRequestProperty(HEADER_ACCEPT,HEADER_VALUE_ACCEPT);
    connection.setRequestProperty(HEADER_ACCEPT_LANGUAGE,getCurrentAcceptLanguage(Locale.getDefault()));
    final String userAgent=mmsConfig.getUserAgent();
    Timber.i("HTTP: User-Agent=" + userAgent);
    connection.setRequestProperty(HEADER_USER_AGENT,userAgent);
    final String uaProfUrlTagName=mmsConfig.getUaProfTagName();
    final String uaProfUrl=mmsConfig.getUaProfUrl();
    if (uaProfUrl != null) {
      Timber.i("HTTP: UaProfUrl=" + uaProfUrl);
      connection.setRequestProperty(uaProfUrlTagName,uaProfUrl);
    }
    addExtraHeaders(connection,mmsConfig);
    if (METHOD_POST.equals(method)) {
      if (pdu == null || pdu.length < 1) {
        Timber.e("HTTP: empty pdu");
        throw new MmsHttpException(0,"Sending empty PDU");
      }
      connection.setDoOutput(true);
      connection.setRequestMethod(METHOD_POST);
      if (mmsConfig.getSupportHttpCharsetHeader()) {
        connection.setRequestProperty(HEADER_CONTENT_TYPE,HEADER_VALUE_CONTENT_TYPE_WITH_CHARSET);
      }
 else {
        connection.setRequestProperty(HEADER_CONTENT_TYPE,HEADER_VALUE_CONTENT_TYPE_WITHOUT_CHARSET);
      }
      logHttpHeaders(connection.getRequestProperties());
      connection.setFixedLengthStreamingMode(pdu.length);
      final OutputStream out=new BufferedOutputStream(connection.getOutputStream());
      out.write(pdu);
      out.flush();
      out.close();
    }
 else     if (METHOD_GET.equals(method)) {
      logHttpHeaders(connection.getRequestProperties());
      connection.setRequestMethod(METHOD_GET);
    }
    final int responseCode=connection.getResponseCode();
    final String responseMessage=connection.getResponseMessage();
    Timber.d("HTTP: " + responseCode + " " + responseMessage);
    logHttpHeaders(connection.getHeaderFields());
    if (responseCode / 100 != 2) {
      throw new MmsHttpException(responseCode,responseMessage);
    }
    final InputStream in=new BufferedInputStream(connection.getInputStream());
    final ByteArrayOutputStream byteOut=new ByteArrayOutputStream();
    final byte[] buf=new byte[4096];
    int count=0;
    while ((count=in.read(buf)) > 0) {
      byteOut.write(buf,0,count);
    }
    in.close();
    final byte[] responseBody=byteOut.toByteArray();
    Timber.d("HTTP: response size=" + (responseBody != null ? responseBody.length : 0));
    return responseBody;
  }
 catch (  MalformedURLException e) {
    Timber.e(e,"HTTP: invalid URL " + urlString);
    throw new MmsHttpException(0,"Invalid URL " + urlString,e);
  }
catch (  ProtocolException e) {
    Timber.e(e,"HTTP: invalid URL protocol " + urlString);
    throw new MmsHttpException(0,"Invalid URL protocol " + urlString,e);
  }
catch (  IOException e) {
    Timber.e(e,"HTTP: IO failure");
    throw new MmsHttpException(0,e);
  }
 finally {
    if (connection != null) {
      connection.disconnect();
    }
  }
}
