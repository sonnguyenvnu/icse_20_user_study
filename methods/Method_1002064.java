/** 
 * EhCache???
 */
@Bean public EhCacheManagerFactoryBean ehcache(){
  EhCacheManagerFactoryBean ehCacheManagerFactoryBean=new EhCacheManagerFactoryBean();
  ehCacheManagerFactoryBean.setConfigLocation(new ClassPathResource("ehcache.xml"));
  return ehCacheManagerFactoryBean;
}
