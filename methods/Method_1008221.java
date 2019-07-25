/** 
 * @return Returns if the positions for each term should be returned ifstored or skip.
 */
public boolean positions(){
  return flagsEnum.contains(Flag.Positions);
}
