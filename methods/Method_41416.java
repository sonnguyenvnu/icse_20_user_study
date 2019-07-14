/** 
 * Create a GroupMatcher that matches job groups containing the given string.
 */
public static GroupMatcher<JobKey> jobGroupContains(String compareTo){
  return GroupMatcher.groupContains(compareTo);
}
