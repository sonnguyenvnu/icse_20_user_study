/** 
 * A helper method to send or retrieve data through HTTP protocol.
 * @param token The token to identify the sending progress.
 * @param url The URL used in a GET request. Null when the method isHTTP_POST_METHOD.
 * @param pdu The data to be POST. Null when the method is HTTP_GET_METHOD.
 * @param method HTTP_POST_METHOD or HTTP_GET_METHOD.
 * @return A byte array which contains the response data.If an HTTP error code is returned, an IOException will be thrown.
 * @throws java.io.IOException if any error occurred on network interface oran HTTP error code(&gt;=400) returned from the server.
 */
public static byte[] httpConnection(Context context,long token,String url,byte[] pdu,int method,boolean isProxySet,String proxyHost,int proxyPort) throws IOException {
  if (url == null) {
    throw new IllegalArgumentException("URL must not be null.");
  }
  Timber.v("httpConnection: params list");
  Timber.v("\ttoken\t\t= " + token);
  Timber.v("\turl\t\t= " + url);
  Timber.v("\tmethod\t\t= " + ((method == HTTP_POST_METHOD) ? "POST" : ((method == HTTP_GET_METHOD) ? "GET" : "UNKNOWN")));
  Timber.v("\tisProxySet\t= " + isProxySet);
  Timber.v("\tproxyHost\t= " + proxyHost);
  Timber.v("\tproxyPort\t= " + proxyPort);
  AndroidHttpClient client=null;
  try {
    URI hostUrl=new URI(url);
    HttpHost target=new HttpHost(hostUrl.getHost(),hostUrl.getPort(),HttpHost.DEFAULT_SCHEME_NAME);
    client=createHttpClient(context);
    HttpRequest req=null;
switch (method) {
case HTTP_POST_METHOD:
      ProgressCallbackEntity entity=new ProgressCallbackEntity(context,token,pdu);
    entity.setContentType("application/vnd.wap.mms-message");
  HttpPost post=new HttpPost(url);
post.setEntity(entity);
req=post;
break;
case HTTP_GET_METHOD:
req=new HttpGet(url);
break;
default :
Timber.e("Unknown HTTP method: " + method + ". Must be one of POST[" + HTTP_POST_METHOD + "] or GET[" + HTTP_GET_METHOD + "].");
return null;
}
HttpParams params=client.getParams();
if (isProxySet) {
ConnRouteParams.setDefaultProxy(params,new HttpHost(proxyHost,proxyPort));
}
req.setParams(params);
req.addHeader(HDR_KEY_ACCEPT,HDR_VALUE_ACCEPT);
{
String xWapProfileTagName=MmsConfig.getUaProfTagName();
String xWapProfileUrl=MmsConfig.getUaProfUrl();
if (xWapProfileUrl != null) {
Timber.d("[HttpUtils] httpConn: xWapProfUrl=" + xWapProfileUrl);
req.addHeader(xWapProfileTagName,xWapProfileUrl);
}
}
String extraHttpParams=MmsConfig.getHttpParams();
if (extraHttpParams != null) {
String line1Number=((TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE)).getLine1Number();
String line1Key=MmsConfig.getHttpParamsLine1Key();
String paramList[]=extraHttpParams.split("\\|");
for (String paramPair : paramList) {
String splitPair[]=paramPair.split(":",2);
if (splitPair.length == 2) {
String name=splitPair[0].trim();
String value=splitPair[1].trim();
if (line1Key != null) {
value=value.replace(line1Key,line1Number);
}
if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(value)) {
req.addHeader(name,value);
}
}
}
}
req.addHeader(HDR_KEY_ACCEPT_LANGUAGE,HDR_VALUE_ACCEPT_LANGUAGE);
HttpResponse response=client.execute(target,req);
StatusLine status=response.getStatusLine();
if (status.getStatusCode() != 200) {
throw new IOException("HTTP error: " + status.getReasonPhrase());
}
HttpEntity entity=response.getEntity();
byte[] body=null;
if (entity != null) {
try {
if (entity.getContentLength() > 0) {
body=new byte[(int)entity.getContentLength()];
DataInputStream dis=new DataInputStream(entity.getContent());
try {
dis.readFully(body);
}
  finally {
try {
dis.close();
}
 catch (IOException e) {
Timber.e("Error closing input stream: " + e.getMessage());
}
}
}
if (entity.isChunked()) {
Timber.v("httpConnection: transfer encoding is chunked");
int bytesTobeRead=MmsConfig.getMaxMessageSize();
byte[] tempBody=new byte[bytesTobeRead];
DataInputStream dis=new DataInputStream(entity.getContent());
try {
int bytesRead=0;
int offset=0;
boolean readError=false;
do {
try {
bytesRead=dis.read(tempBody,offset,bytesTobeRead);
}
 catch (IOException e) {
readError=true;
Timber.e("httpConnection: error reading input stream" + e.getMessage());
break;
}
if (bytesRead > 0) {
bytesTobeRead-=bytesRead;
offset+=bytesRead;
}
}
 while (bytesRead >= 0 && bytesTobeRead > 0);
if (bytesRead == -1 && offset > 0 && !readError) {
body=new byte[offset];
System.arraycopy(tempBody,0,body,0,offset);
Timber.v("httpConnection: Chunked response length [" + Integer.toString(offset) + "]");
}
 else {
Timber.e("httpConnection: Response entity too large or empty");
}
}
  finally {
try {
dis.close();
}
 catch (IOException e) {
Timber.e("Error closing input stream: " + e.getMessage());
}
}
}
}
  finally {
if (entity != null) {
entity.consumeContent();
}
}
}
return body;
}
 catch (URISyntaxException e) {
handleHttpConnectionException(e,url);
}
catch (IllegalStateException e) {
handleHttpConnectionException(e,url);
}
catch (IllegalArgumentException e) {
handleHttpConnectionException(e,url);
}
catch (SocketException e) {
handleHttpConnectionException(e,url);
}
catch (Exception e) {
handleHttpConnectionException(e,url);
}
 finally {
if (client != null) {
client.close();
}
}
return null;
}
