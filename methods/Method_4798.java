/** 
 * Called to indicate that a NAL unit has ended.
 * @param discardPadding The number of excess bytes that were passed to{@link #appendToNalUnit(byte[],int,int)}, which should be discarded.
 * @return Whether the ended NAL unit is of the target type.
 */
public boolean endNalUnit(int discardPadding){
  if (!isFilling) {
    return false;
  }
  nalLength-=discardPadding;
  isFilling=false;
  isCompleted=true;
  return true;
}
