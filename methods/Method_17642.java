/** 
 * Returns all variations of this policy based on the configuration parameters. 
 */
public static Set<Policy> policies(Config config){
  WindowTinyLfuSettings settings=new WindowTinyLfuSettings(config);
  return settings.percentMain().stream().map(percentMain -> new WindowTinyLfuPolicy(percentMain,settings)).collect(toSet());
}
