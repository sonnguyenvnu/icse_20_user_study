/** 
 * @param number number
 * @return a Ssh2MessageNumber object.
 */
public static Ssh2MessageNumber register(Ssh2MessageNumber number){
  return registry.put(number.value(),number);
}
