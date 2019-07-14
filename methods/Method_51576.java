/** 
 * See  {@link #getRuleSets(String,RuleSetFactory)}. In addition, the loading of the rules is benchmarked.
 * @param rulesets the string with the rulesets to load
 * @param factory the ruleset factory
 * @return the rulesets
 * @throws IllegalArgumentException if rulesets is empty (means, no rules have been found) or if a ruleset couldn't be found.
 */
public static RuleSets getRuleSetsWithBenchmark(String rulesets,RuleSetFactory factory){
  try (TimedOperation to=TimeTracker.startOperation(TimedOperationCategory.LOAD_RULES)){
    return getRuleSets(rulesets,factory);
  }
 }
