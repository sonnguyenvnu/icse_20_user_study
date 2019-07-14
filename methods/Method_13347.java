@Override public void afterSingletonsInstantiated(){
  loadBalancerInterceptorBean=retryLoadBalancerInterceptor != null ? retryLoadBalancerInterceptor : loadBalancerInterceptor;
}
