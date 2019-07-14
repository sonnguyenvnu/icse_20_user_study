/** 
 * @see RuleChainVisitor#visitAll(List,RuleContext)
 */
@Override public void visitAll(List<Node> nodes,RuleContext ctx){
  initialize();
  clear();
  try (TimedOperation to=TimeTracker.startOperation(TimedOperationCategory.RULECHAIN_VISIT)){
    indexNodes(nodes,ctx);
  }
   try (TimedOperation to=TimeTracker.startOperation(TimedOperationCategory.RULECHAIN_RULE)){
    for (    Map.Entry<RuleSet,List<Rule>> entry : ruleSetRules.entrySet()) {
      RuleSet ruleSet=entry.getKey();
      if (!ruleSet.applies(ctx.getSourceCodeFile())) {
        continue;
      }
      for (      Rule rule : entry.getValue()) {
        int visits=0;
        if (!RuleSet.applies(rule,ctx.getLanguageVersion())) {
          continue;
        }
        try (TimedOperation rcto=TimeTracker.startOperation(TimedOperationCategory.RULECHAIN_RULE,rule.getName())){
          final List<String> nodeNames=rule.getRuleChainVisits();
          for (int j=0; j < nodeNames.size(); j++) {
            List<Node> ns=nodeNameToNodes.get(nodeNames.get(j));
            for (            Node node : ns) {
              Rule actualRule=rule;
              while (actualRule instanceof RuleReference) {
                actualRule=((RuleReference)actualRule).getRule();
              }
              visit(actualRule,node,ctx);
            }
            visits+=ns.size();
          }
          rcto.close(visits);
        }
 catch (        RuntimeException e) {
          if (ctx.isIgnoreExceptions()) {
            ctx.getReport().addError(new Report.ProcessingError(e,ctx.getSourceCodeFilename()));
            if (LOG.isLoggable(Level.WARNING)) {
              LOG.log(Level.WARNING,"Exception applying rule " + rule.getName() + " on file " + ctx.getSourceCodeFilename() + ", continuing with next rule",e);
            }
          }
 else {
            throw e;
          }
        }
      }
    }
  }
 }
