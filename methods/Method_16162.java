@Bean public WebMvcConfigurer webMvcConfigurer(List<HandlerMethodArgumentResolver> handlerMethodArgumentResolvers){
  return new WebMvcConfigurerAdapter(){
    @Override public void addArgumentResolvers(    List<HandlerMethodArgumentResolver> argumentResolvers){
      super.addArgumentResolvers(argumentResolvers);
      argumentResolvers.addAll(handlerMethodArgumentResolvers);
    }
    @Override public void addInterceptors(    InterceptorRegistry registry){
      registry.addInterceptor(new HandlerInterceptorAdapter(){
        @Override public void afterCompletion(        HttpServletRequest request,        HttpServletResponse response,        Object handler,        Exception ex) throws Exception {
          ThreadLocalUtils.clear();
        }
      }
);
    }
  }
;
}
