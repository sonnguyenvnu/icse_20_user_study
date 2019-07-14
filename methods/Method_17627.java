/** 
 * Returns all variations of this policy based on the configuration parameters. 
 */
public static Set<Policy> policies(Config config){
  RandomWindowTinyLfuSettings settings=new RandomWindowTinyLfuSettings(config);
  return settings.percentMain().stream().map(percentMain -> new RandomWindowTinyLfuPolicy(percentMain,settings)).collect(toSet());
}
