/** 
 * @return the {@code ClassRule}s that can transform the block that runs each method in the tested class.
 */
protected List<TestRule> classRules(){
  ClassRuleCollector collector=new ClassRuleCollector();
  testClass.collectAnnotatedMethodValues(null,ClassRule.class,TestRule.class,collector);
  testClass.collectAnnotatedFieldValues(null,ClassRule.class,TestRule.class,collector);
  return collector.getOrderedRules();
}
