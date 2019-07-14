/** 
 * Create a GroupMatcher that matches groups starting with the given string.
 */
public static <T extends Key<T>>GroupMatcher<T> anyGroup(){
  return new GroupMatcher<T>("",StringOperatorName.ANYTHING);
}
