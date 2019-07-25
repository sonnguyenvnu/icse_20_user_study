/** 
 * Returns a newly-created  {@link RetryingHttpClient} based on the properties of this builder.
 */
@Override public RetryingHttpClient build(Client<HttpRequest,HttpResponse> delegate){
  if (needsContentInStrategy) {
    return new RetryingHttpClient(delegate,retryStrategyWithContent(),maxTotalAttempts(),responseTimeoutMillisForEachAttempt(),useRetryAfter,contentPreviewLength);
  }
  return new RetryingHttpClient(delegate,retryStrategy(),maxTotalAttempts(),responseTimeoutMillisForEachAttempt(),useRetryAfter);
}
