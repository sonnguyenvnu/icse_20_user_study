@Override public void added(Provider<Rule> provider,Rule element){
  String ruleUID=element.getUID();
  Rule resolvedRule=element;
  try {
    resolvedRule=resolveRuleByTemplate(element);
  }
 catch (  IllegalArgumentException e) {
    logger.debug("Added rule '{}' is invalid",ruleUID,e);
  }
  super.added(provider,element);
  if (element != resolvedRule) {
    if (provider instanceof ManagedRuleProvider) {
      update(resolvedRule);
    }
 else {
      super.updated(provider,element,resolvedRule);
    }
  }
}
