@Override public void added(RuleTemplate element){
  String templateUID=element.getUID();
  Set<String> rules=new HashSet<String>();
synchronized (this) {
    Set<String> rulesForResolving=mapTemplateToRules.get(templateUID);
    if (rulesForResolving != null) {
      rules.addAll(rulesForResolving);
    }
  }
  for (  String rUID : rules) {
    try {
      Rule unresolvedRule=get(rUID);
      Rule resolvedRule=resolveRuleByTemplate(unresolvedRule);
      Provider<Rule> provider=getProvider(rUID);
      if (provider instanceof ManagedRuleProvider) {
        update(resolvedRule);
      }
 else {
        updated(provider,unresolvedRule,unresolvedRule);
      }
    }
 catch (    IllegalArgumentException e) {
      logger.error("Resolving the rule '{}' by template '{}' failed",rUID,templateUID,e);
    }
  }
}
