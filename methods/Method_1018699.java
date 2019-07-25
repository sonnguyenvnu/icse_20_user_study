/** 
 * @param number number
 * @return a Ssh2DisconnectionReasonCode object.
 */
public static Ssh2DisconnectionReasonCode register(Ssh2DisconnectionReasonCode number){
  return registry.put(number.value(),number);
}
