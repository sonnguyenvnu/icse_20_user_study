@Bean @Scope("prototype") @ConditionalOnClass(name="com.alibaba.csp.sentinel.SphU") @ConditionalOnProperty(name="feign.sentinel.enabled",havingValue="true") Feign.Builder feignSentinelBuilder(BeanFactory beanFactory){
  return SeataSentinelFeignBuilder.builder(beanFactory);
}
