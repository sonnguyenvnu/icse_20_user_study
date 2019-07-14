/** 
 * Matches value against single rule. By default performs <code>equals</code> on value against the rule.
 */
@Override public boolean accept(final V value,final R rule,final boolean include){
  return value.equals(rule);
}
