/** 
 * Create a GroupMatcher that matches groups containing the given string.
 */
public static <T extends Key<T>>GroupMatcher<T> groupContains(String compareTo){
  return new GroupMatcher<T>(compareTo,StringOperatorName.CONTAINS);
}
