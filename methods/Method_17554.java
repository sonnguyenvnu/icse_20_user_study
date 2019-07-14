/** 
 * Returns all variations of this policy based on the configuration parameters. 
 */
public static Set<Policy> policies(Config config){
  OhcSettings settings=new OhcSettings(config);
  return settings.policy().stream().map(policy -> new OhcPolicy(settings,policy)).collect(toSet());
}
