@Override public HttpResponse serve(ServiceRequestContext ctx,HttpRequest req) throws Exception {
  return HttpResponse.from(AuthorizerUtil.authorize(authorizer,ctx,req).handleAsync((result,cause) -> {
    try {
      if (cause == null) {
        if (result != null) {
          return result ? successHandler.authSucceeded(delegate(),ctx,req) : failureHandler.authFailed(delegate(),ctx,req,null);
        }
        cause=AuthorizerUtil.newNullResultException(authorizer);
      }
      return failureHandler.authFailed(delegate(),ctx,req,cause);
    }
 catch (    Exception e) {
      return Exceptions.throwUnsafely(e);
    }
  }
,ctx.contextAwareEventLoop()));
}
