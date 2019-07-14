/** 
 * Returns all of the policy variations that have been configured. 
 */
public static Set<Policy> policy(BasicSettings settings,String name){
  Function<Config,Set<Policy>> factory=FACTORIES.get(name.toLowerCase(US));
  checkNotNull(factory,"%s not found",name);
  return factory.apply(settings.config());
}
