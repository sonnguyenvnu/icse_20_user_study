@Override HttpResponse handleRequest(HttpRequest req) throws TwitterException {
  prepareOkHttpClient();
  OkHttpResponse res=null;
  Request.Builder requestBuilder=new Request.Builder();
  requestBuilder.url(req.getURL()).headers(getHeaders(req));
switch (req.getMethod()) {
case HEAD:
case PUT:
    break;
case DELETE:
  requestBuilder.delete();
break;
case GET:
requestBuilder.get();
break;
case POST:
try {
requestBuilder.post(getRequestBody(req));
}
 catch (UnsupportedEncodingException e) {
throw new TwitterException(e.getMessage(),e);
}
break;
}
final Request request=requestBuilder.build();
int retriedCount;
int retry=CONF.getHttpRetryCount() + 1;
for (retriedCount=0; retriedCount < retry; retriedCount++) {
int responseCode=-1;
try {
Call call=okHttpClient.newCall(request);
res=new OkHttpResponse(call,okHttpClient,CONF);
lastRequestProtocol=res.getProtocol();
responseCode=res.getStatusCode();
if (logger.isDebugEnabled()) {
logger.debug("Response: ");
Map<String,List<String>> responseHeaders=res.getResponseHeaderFields();
for (String key : responseHeaders.keySet()) {
List<String> values=responseHeaders.get(key);
for (String value : values) {
if (key != null) {
logger.debug(key + ": " + value);
}
 else {
logger.debug(value);
}
}
}
}
if (responseCode < OK || (responseCode != FOUND && MULTIPLE_CHOICES <= responseCode)) {
if (responseCode == ENHANCE_YOUR_CLAIM || responseCode == BAD_REQUEST || responseCode < INTERNAL_SERVER_ERROR || retriedCount == CONF.getHttpRetryCount()) {
throw new TwitterException(res.asString(),res);
}
}
 else {
break;
}
}
 catch (IOException e) {
if (retriedCount == CONF.getHttpRetryCount()) {
throw new TwitterException(e.getMessage(),e,responseCode);
}
}
try {
if (logger.isDebugEnabled() && res != null) {
res.asString();
}
logger.debug("Sleeping " + CONF.getHttpRetryIntervalSeconds() + " seconds until the next retry.");
Thread.sleep(CONF.getHttpRetryIntervalSeconds() * 1000);
}
 catch (InterruptedException ignore) {
}
}
return res;
}
