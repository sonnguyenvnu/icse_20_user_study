/** 
 * Create a NameMatcher that matches trigger names equaling the given string.
 */
public static NameMatcher<TriggerKey> triggerNameEquals(String compareTo){
  return NameMatcher.nameEquals(compareTo);
}
