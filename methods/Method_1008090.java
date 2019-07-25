private <T>void execute(String method,String uri,Map<String,String> params,HttpEntity entity,BiFunction<XContentParser,XContentType,T> parser,Consumer<? super T> listener){
  java.util.function.Supplier<ThreadContext.StoredContext> contextSupplier=threadPool.getThreadContext().newRestorableContext(true);
class RetryHelper extends AbstractRunnable {
    @Override protected void doRun() throws Exception {
      client.performRequestAsync(method,uri,params,entity,new ResponseListener(){
        @Override public void onSuccess(        org.elasticsearch.client.Response response){
          try (ThreadContext.StoredContext ctx=contextSupplier.get()){
            assert ctx != null;
            T parsedResponse;
            try {
              HttpEntity responseEntity=response.getEntity();
              InputStream content=responseEntity.getContent();
              XContentType xContentType=null;
              if (responseEntity.getContentType() != null) {
                final String mimeType=ContentType.parse(responseEntity.getContentType().getValue()).getMimeType();
                xContentType=XContentType.fromMediaType(mimeType);
              }
              if (xContentType == null) {
                try {
                  throw new ElasticsearchException("Response didn't include Content-Type: " + bodyMessage(response.getEntity()));
                }
 catch (                IOException e) {
                  ElasticsearchException ee=new ElasticsearchException("Error extracting body from response");
                  ee.addSuppressed(e);
                  throw ee;
                }
              }
              try (XContentParser xContentParser=xContentType.xContent().createParser(NamedXContentRegistry.EMPTY,content)){
                parsedResponse=parser.apply(xContentParser,xContentType);
              }
 catch (              ParsingException e) {
                throw new ElasticsearchException("Error parsing the response, remote is likely not an Elasticsearch instance",e);
              }
            }
 catch (            IOException e) {
              throw new ElasticsearchException("Error deserializing response, remote is likely not an Elasticsearch instance",e);
            }
            listener.accept(parsedResponse);
          }
         }
        @Override public void onFailure(        Exception e){
          try (ThreadContext.StoredContext ctx=contextSupplier.get()){
            assert ctx != null;
            if (e instanceof ResponseException) {
              ResponseException re=(ResponseException)e;
              if (RestStatus.TOO_MANY_REQUESTS.getStatus() == re.getResponse().getStatusLine().getStatusCode()) {
                if (retries.hasNext()) {
                  TimeValue delay=retries.next();
                  logger.trace((Supplier<?>)() -> new ParameterizedMessage("retrying rejected search after [{}]",delay),e);
                  countSearchRetry.run();
                  threadPool.schedule(delay,ThreadPool.Names.SAME,RetryHelper.this);
                  return;
                }
              }
              e=wrapExceptionToPreserveStatus(re.getResponse().getStatusLine().getStatusCode(),re.getResponse().getEntity(),re);
            }
 else             if (e instanceof ContentTooLongException) {
              e=new IllegalArgumentException("Remote responded with a chunk that was too large. Use a smaller batch size.",e);
            }
            fail.accept(e);
          }
         }
      }
);
    }
    @Override public void onFailure(    Exception t){
      fail.accept(t);
    }
  }
  new RetryHelper().run();
}
