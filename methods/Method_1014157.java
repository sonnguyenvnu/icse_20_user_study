@Activate public void activate(){
  injector=RulesStandaloneSetup.getInjector();
  triggerManager=new RuleTriggerManager(injector);
  logger.debug("Started rule engine");
  for (  String ruleModelName : modelRepository.getAllModelNamesOfType("rules")) {
    EObject model=modelRepository.getModel(ruleModelName);
    if (model instanceof RuleModel) {
      RuleModel ruleModel=(RuleModel)model;
      triggerManager.addRuleModel(ruleModel);
    }
  }
  itemRegistry.addRegistryChangeListener(this);
  modelRepository.addModelRepositoryChangeListener(this);
  for (  Item item : itemRegistry.getItems()) {
    internalItemAdded(item);
  }
  scheduleStartupRules();
}
