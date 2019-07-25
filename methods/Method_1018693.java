/** 
 * @param number number
 * @return a IpV4SecurityOptionHandlingRestrictions object.
 */
public static IpV4SecurityOptionHandlingRestrictions register(IpV4SecurityOptionHandlingRestrictions number){
  return registry.put(number.value(),number);
}
