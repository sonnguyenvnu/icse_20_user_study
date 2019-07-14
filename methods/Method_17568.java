/** 
 * Returns all variations of this policy based on the configuration parameters. 
 */
public static Set<Policy> policies(Config config,EvictionPolicy policy){
  BasicSettings settings=new BasicSettings(config);
  return settings.admission().stream().map(admission -> new SampledPolicy(admission,policy,config)).collect(toSet());
}
