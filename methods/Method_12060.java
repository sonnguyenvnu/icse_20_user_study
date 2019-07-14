/** 
 * Returns rule instances in the order how they should be applied, i.e. inner-to-outer. VisibleForTesting
 */
List<Object> getSortedRules(){
  List<Object> result=new ArrayList<Object>();
  for (  RuleEntry entry : getSortedEntries()) {
    result.add(entry.rule);
  }
  return result;
}
