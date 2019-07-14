/** 
 * Returns all variations of this policy based on the configuration parameters. 
 */
public static Set<Policy> policies(Config config){
  CollisionSettings settings=new CollisionSettings(config);
  return settings.density().map(density -> new CollisionPolicy(settings,density)).collect(toSet());
}
