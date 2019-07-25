/** 
 * create an instance of  {@link ProtocolSwitch} according to switch index
 * @param index the switch index which you want to set true
 * @return ProtocolSwitchStatus with initialized bit set.
 */
public static ProtocolSwitch create(int[] index){
  ProtocolSwitch status=new ProtocolSwitch();
  for (int i=0; i < index.length; ++i) {
    status.turnOn(index[i]);
  }
  return status;
}
