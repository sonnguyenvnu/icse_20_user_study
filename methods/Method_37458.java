/** 
 * Applies rules on given flag. Flag is only changed if at least one rule matched. Otherwise, the same value is returned. This way you can chain several rules and have the rule engine change the flag only when a rule is matched.
 */
public boolean apply(final V value,final boolean blacklist,boolean flag){
  if (rules == null) {
    return flag;
  }
  if (blacklist) {
    flag=processExcludes(value,flag);
    flag=processIncludes(value,flag);
  }
 else {
    flag=processIncludes(value,flag);
    flag=processExcludes(value,flag);
  }
  return flag;
}
