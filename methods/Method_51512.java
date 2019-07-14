/** 
 * Calculate a summary of violation counts per fully classified class name.
 * @return violations per class name
 */
public Map<String,Integer> getCountSummary(){
  Map<String,Integer> summary=new HashMap<>();
  for (  RuleViolation rv : violationTree) {
    String key=keyFor(rv);
    Integer o=summary.get(key);
    summary.put(key,o == null ? NumericConstants.ONE : o + 1);
  }
  return summary;
}
