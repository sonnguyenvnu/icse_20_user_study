/** 
 * Create a GroupMatcher that matches trigger groups equaling the given string.
 */
public static GroupMatcher<TriggerKey> triggerGroupEquals(String compareTo){
  return GroupMatcher.groupEquals(compareTo);
}
