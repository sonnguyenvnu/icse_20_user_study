private void runInterceptors(){
  if (!interceptors.isEmpty()) {
    if (modelInterceptorCallbacks != null) {
      for (      ModelInterceptorCallback callback : modelInterceptorCallbacks) {
        callback.onInterceptorsStarted(this);
      }
    }
    timer.start("Interceptors executed");
    for (    Interceptor interceptor : interceptors) {
      interceptor.intercept(modelsBeingBuilt);
    }
    timer.stop();
    if (modelInterceptorCallbacks != null) {
      for (      ModelInterceptorCallback callback : modelInterceptorCallbacks) {
        callback.onInterceptorsFinished(this);
      }
    }
  }
  modelInterceptorCallbacks=null;
}
