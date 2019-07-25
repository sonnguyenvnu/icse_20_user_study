/** 
 * ????????? ????????,?????????????,?????
 * @param callback ??
 * @return ???????????????? {@link Router#emptyNavigationDisposable},????????????request??
 */
@AnyThread @NonNull public synchronized NavigationDisposable navigate(@Nullable final Callback callback){
  RouterRequest originalRequest=null;
  try {
    useDefaultApplication();
    onCheck();
    isFinish=true;
    originalRequest=build();
    final InterceptorCallback interceptorCallback=new InterceptorCallback(originalRequest,callback);
    if (originalRequest.fragment != null) {
      Router.mNavigationDisposableList.add(interceptorCallback);
    }
    if (Utils.getActivityFromContext(originalRequest.context) != null) {
      Router.mNavigationDisposableList.add(interceptorCallback);
    }
    realNavigate(originalRequest,customInterceptors,interceptorCallback);
    return interceptorCallback;
  }
 catch (  Exception e) {
    RouterErrorResult errorResult=new RouterErrorResult(originalRequest,e);
    RouterUtil.errorCallback(callback,errorResult);
  }
 finally {
    context=null;
    fragment=null;
    scheme=null;
    url=null;
    host=null;
    path=null;
    requestCode=null;
    queryMap=null;
    bundle=null;
    intentConsumer=null;
    beforJumpAction=null;
    afterJumpAction=null;
  }
  return Router.emptyNavigationDisposable;
}
