@Override public boolean test(String url){
  for (  RequestInterceptor interceptor : mInterceptors) {
    if (interceptor.test(url)) {
      return true;
    }
  }
  return false;
}
