/** 
 * Called to indicate that a NAL unit has started.
 * @param type The type of the NAL unit.
 */
public void startNalUnit(int type){
  Assertions.checkState(!isFilling);
  isFilling=type == targetType;
  if (isFilling) {
    nalLength=3;
    isCompleted=false;
  }
}
