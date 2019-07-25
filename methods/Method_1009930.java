public Navigator interceptors(Class<? extends RouterInterceptor>... interceptorClassArr){
  Utils.checkNullPointer(interceptorClassArr,"interceptorClassArr");
  if (interceptorClassArr != null) {
    lazyInitCustomInterceptors(interceptorClassArr.length);
    customInterceptors.addAll(Arrays.asList(interceptorClassArr));
  }
  return this;
}
