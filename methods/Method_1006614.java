/** 
 * create an instance of  {@link ProtocolSwitch} according to byte value
 * @param value
 * @return ProtocolSwitchStatus with initialized bit set.
 */
public static ProtocolSwitch create(int value){
  ProtocolSwitch status=new ProtocolSwitch();
  status.setBs(toBitSet(value));
  return status;
}
