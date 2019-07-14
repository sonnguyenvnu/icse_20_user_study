/** 
 * Create a GroupMatcher that matches trigger groups containing the given string.
 */
public static GroupMatcher<TriggerKey> triggerGroupContains(String compareTo){
  return GroupMatcher.groupContains(compareTo);
}
