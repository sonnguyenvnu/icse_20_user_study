/** 
 * @param target the test case instance
 * @return a list of MethodRules that should be applied when executing thistest
 */
protected List<MethodRule> rules(Object target){
  RuleCollector<MethodRule> collector=new RuleCollector<MethodRule>();
  getTestClass().collectAnnotatedMethodValues(target,Rule.class,MethodRule.class,collector);
  getTestClass().collectAnnotatedFieldValues(target,Rule.class,MethodRule.class,collector);
  return collector.result;
}
