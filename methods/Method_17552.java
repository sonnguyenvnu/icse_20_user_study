/** 
 * Returns all variations of this policy based on the configuration parameters. 
 */
public static Set<Policy> policies(Config config){
  return ImmutableSet.of(new ExpiringMapPolicy(config));
}
