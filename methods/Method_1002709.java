/** 
 * Creates a new HTTP response that delegates to the  {@link HttpResponse} produced by the specified{@link CompletionStage}. If the specified  {@link CompletionStage} fails, the returned response will beclosed with the same cause as well.
 */
static HttpResponse from(CompletionStage<? extends HttpResponse> stage){
  requireNonNull(stage,"stage");
  final DeferredHttpResponse res=new DeferredHttpResponse();
  stage.handle((delegate,thrown) -> {
    if (thrown != null) {
      res.close(Exceptions.peel(thrown));
    }
 else     if (delegate == null) {
      res.close(new NullPointerException("delegate stage produced a null response: " + stage));
    }
 else {
      res.delegate(delegate);
    }
    return null;
  }
);
  return res;
}
