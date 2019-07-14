/** 
 * Returns all variations of this policy based on the configuration parameters. 
 */
public static Set<Policy> policies(Config config){
  LruWindowTinyLfuSettings settings=new LruWindowTinyLfuSettings(config);
  return settings.percentMain().stream().map(percentMain -> new LruWindowTinyLfuPolicy(percentMain,settings)).collect(toSet());
}
