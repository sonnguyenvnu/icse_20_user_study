/** 
 * Returns all variations of this policy based on the configuration parameters. 
 */
public static Set<Policy> policies(Config config){
  FullySegmentedWindowTinyLfuSettings settings=new FullySegmentedWindowTinyLfuSettings(config);
  return settings.percentMain().stream().map(percentMain -> new FullySegmentedWindowTinyLfuPolicy(percentMain,settings)).collect(toSet());
}
