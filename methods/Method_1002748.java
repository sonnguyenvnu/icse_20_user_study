/** 
 * Creates a new  {@link RpcResponse} that is completed successfully or exceptionally based on thecompletion of the specified  {@link CompletionStage}.
 */
static RpcResponse from(CompletionStage<?> stage){
  requireNonNull(stage,"stage");
  final DefaultRpcResponse res=new DefaultRpcResponse();
  stage.handle((value,cause) -> {
    if (cause != null) {
      res.completeExceptionally(cause);
    }
 else     if (value instanceof RpcResponse) {
      ((RpcResponse)value).handle((rpcResponseResult,rpcResponseCause) -> {
        if (rpcResponseCause != null) {
          res.completeExceptionally(Exceptions.peel(rpcResponseCause));
        }
 else {
          res.complete(rpcResponseResult);
        }
        return null;
      }
);
    }
 else {
      res.complete(value);
    }
    return null;
  }
);
  return res;
}
