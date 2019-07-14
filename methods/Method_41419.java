/** 
 * Create a GroupMatcher that matches job groups starting with the given string.
 */
public static GroupMatcher<JobKey> anyJobGroup(){
  return GroupMatcher.anyGroup();
}
