/** 
 * Returns <code>true</code> if rule engine has at least one rule set.
 */
public boolean hasRules(){
  if (rules == null) {
    return false;
  }
  return !rules.isEmpty();
}
