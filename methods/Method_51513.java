/** 
 * Calculate a summary of violations per rule.
 * @return a Map summarizing the Report: String (rule name) -&gt; Integer (countof violations)
 */
public Map<String,Integer> getSummary(){
  Map<String,Integer> summary=new HashMap<>();
  for (  RuleViolation rv : violations) {
    String name=rv.getRule().getName();
    if (!summary.containsKey(name)) {
      summary.put(name,NumericConstants.ZERO);
    }
    Integer count=summary.get(name);
    summary.put(name,count + 1);
  }
  return summary;
}
