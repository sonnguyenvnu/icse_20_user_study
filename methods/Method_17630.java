/** 
 * Returns all variations of this policy based on the configuration parameters. 
 */
public static Set<Policy> policies(Config config){
  S4WindowTinyLfuSettings settings=new S4WindowTinyLfuSettings(config);
  return settings.percentMain().stream().map(percentMain -> new S4WindowTinyLfuPolicy(percentMain,settings)).collect(toSet());
}
