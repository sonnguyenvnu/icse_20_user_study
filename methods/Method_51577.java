public static RuleSetFactory getRulesetFactory(final PMDConfiguration configuration,final ResourceLoader resourceLoader){
  return new RuleSetFactory(resourceLoader,configuration.getMinimumPriority(),true,configuration.isRuleSetFactoryCompatibilityEnabled());
}
