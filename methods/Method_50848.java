/** 
 * Initialize the mapper with the given rulesets.
 * @param rs The rulesets from which to retrieve rules.
 */
public void initialize(final RuleSets rs){
  for (  final Rule r : rs.getAllRules()) {
    ruleByClassName.put(r.getRuleClass(),r);
  }
}
