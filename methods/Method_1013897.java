/** 
 * This method is used to create a  {@link CompositeModuleHandlerFactory} that handles all composite{@link ModuleType}s. Called from DS to activate the rule engine component.
 */
@Activate protected void activate(){
  compositeFactory=new CompositeModuleHandlerFactory(mtRegistry,this);
  for (  Rule rule : ruleRegistry.getAll()) {
    String uid=rule.getUID();
    final Storage<Boolean> disabledRulesStorage=this.disabledRulesStorage;
    if (disabledRulesStorage == null || disabledRulesStorage.get(uid) == null) {
      setEnabled(uid,true);
    }
  }
}
