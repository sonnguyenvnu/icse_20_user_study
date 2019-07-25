@Override public O serve(ServiceRequestContext ctx,I req) throws Exception {
  return responseConverter.apply(strategy.accept(ctx,req).handleAsync((accept,thrown) -> {
    try {
      if (thrown != null || !accept) {
        return onFailure(ctx,req,thrown);
      }
      return onSuccess(ctx,req);
    }
 catch (    Exception e) {
      return Exceptions.throwUnsafely(e);
    }
  }
,ctx.contextAwareEventLoop()));
}
