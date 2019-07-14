/** 
 * Returns all variations of this policy based on the configuration parameters. 
 */
public static Set<Policy> policies(Config config){
  HillClimberWindowTinyLfuSettings settings=new HillClimberWindowTinyLfuSettings(config);
  Set<Policy> policies=new HashSet<>();
  for (  HillClimberType climber : settings.strategy()) {
    for (    double percentMain : settings.percentMain()) {
      policies.add(new HillClimberWindowTinyLfuPolicy(climber,percentMain,settings));
    }
  }
  return policies;
}
