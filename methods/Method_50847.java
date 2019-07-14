/** 
 * Finds a rule instance for the given rule class name
 * @param className The name of the rule class that generated the cache entry
 * @return The requested rule
 */
public Rule getRuleForClass(final String className){
  return ruleByClassName.get(className);
}
