/** 
 * Returns a newly-created  {@link RetryingRpcClient} based on the properties of this builder.
 */
@Override public RetryingRpcClient build(Client<RpcRequest,RpcResponse> delegate){
  return new RetryingRpcClient(delegate,retryStrategyWithContent(),maxTotalAttempts(),responseTimeoutMillisForEachAttempt());
}
