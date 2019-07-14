/** 
 * Create a NameMatcher that matches job names equaling the given string.
 */
public static NameMatcher<JobKey> jobNameEquals(String compareTo){
  return NameMatcher.nameEquals(compareTo);
}
