/** 
 * ???????Adjuster?
 * @param index ?????Adjuster????
 * @return ????Adjuster?????????null?
 */
public Adjuster removeAdjuster(int index){
  int realIndex=SYSTEM_ADJUSTER_SIZE + index;
  if (realIndex > SYSTEM_ADJUSTER_SIZE - 1 && realIndex < adjusterList.size()) {
    Adjuster remove=adjusterList.remove(realIndex);
    remove.detach(this);
    postInvalidate();
    return remove;
  }
  return null;
}
