/** 
 * Returns all variations of this policy based on the configuration parameters. 
 */
public static Set<Policy> policies(Config config){
  FeedbackWindowTinyLfuSettings settings=new FeedbackWindowTinyLfuSettings(config);
  return settings.percentMain().stream().map(percentMain -> new FeedbackWindowTinyLfuPolicy(percentMain,settings)).collect(toSet());
}
