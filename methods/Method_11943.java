/** 
 * Create a new  {@code RuleChain}, which encloses the given  {@link TestRule} withthe rules of the current  {@code RuleChain}.
 * @param enclosedRule the rule to enclose; must not be {@code null}.
 * @return a new {@code RuleChain}.
 * @throws NullPointerException if the argument {@code enclosedRule} is {@code null}
 */
public RuleChain around(TestRule enclosedRule){
  if (enclosedRule == null) {
    throw new NullPointerException("The enclosed rule must not be null");
  }
  List<TestRule> rulesOfNewChain=new ArrayList<TestRule>();
  rulesOfNewChain.add(enclosedRule);
  rulesOfNewChain.addAll(rulesStartingWithInnerMost);
  return new RuleChain(rulesOfNewChain);
}
