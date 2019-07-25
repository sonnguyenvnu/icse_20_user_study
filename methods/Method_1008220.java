/** 
 * @return <code>true</code> if term offsets should be returned. Otherwise<code>false</code>
 */
public boolean offsets(){
  return flagsEnum.contains(Flag.Offsets);
}
