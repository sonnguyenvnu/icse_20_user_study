@Bean @ConditionalOnProperty(prefix="hsweb.authorize.two-factor",name="enable",havingValue="true") @Order(100) public WebMvcConfigurer twoFactorHandlerConfigurer(TwoFactorValidatorManager manager){
  return new WebMvcConfigurerAdapter(){
    @Override public void addInterceptors(    InterceptorRegistry registry){
      registry.addInterceptor(new TwoFactorHandlerInterceptorAdapter(manager));
      super.addInterceptors(registry);
    }
  }
;
}
