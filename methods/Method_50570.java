/** 
 * ??index???  {@link Adjuster}?
 * @param index ?????Adjuster?index?
 * @return index???Adjuster?????????null?
 */
public Adjuster getAdjuster(int index){
  int realIndex=SYSTEM_ADJUSTER_SIZE + index;
  if (realIndex > SYSTEM_ADJUSTER_SIZE - 1 && realIndex < adjusterList.size()) {
    return adjusterList.get(realIndex);
  }
  return null;
}
