/** 
 * ??????????
 * @return
 */
public boolean isOffline(){
  return MachineInfoEnum.AvailableEnum.NO.getValue() == this.available;
}
