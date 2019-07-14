/** 
 * Feign post processor hmily feign bean post processor.
 * @return the hmily feign bean post processor
 */
@Bean public HmilyFeignBeanPostProcessor feignPostProcessor(){
  return new HmilyFeignBeanPostProcessor();
}
