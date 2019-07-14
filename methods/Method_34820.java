/** 
 * Retrieve instance of  {@link HystrixPropertiesStrategy} to use based on order of precedence as defined in {@link HystrixPlugins} class header.<p> Override default by using  {@link #registerPropertiesStrategy(HystrixPropertiesStrategy)} or setting property (via Archaius): <code>hystrix.plugin.HystrixPropertiesStrategy.implementation</code> with the fullclassname to load.
 * @return {@link HystrixPropertiesStrategy} implementation to use
 */
public HystrixPropertiesStrategy getPropertiesStrategy(){
  if (propertiesFactory.get() == null) {
    Object impl=getPluginImplementation(HystrixPropertiesStrategy.class);
    if (impl == null) {
      propertiesFactory.compareAndSet(null,HystrixPropertiesStrategyDefault.getInstance());
    }
 else {
      propertiesFactory.compareAndSet(null,(HystrixPropertiesStrategy)impl);
    }
  }
  return propertiesFactory.get();
}
