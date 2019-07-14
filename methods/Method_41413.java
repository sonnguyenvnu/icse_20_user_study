/** 
 * Create a GroupMatcher that matches job groups equaling the given string.
 */
public static GroupMatcher<JobKey> jobGroupEquals(String compareTo){
  return GroupMatcher.groupEquals(compareTo);
}
