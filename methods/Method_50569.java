/** 
 * ??????  {@link Adjuster}
 * @return ?????? {@link Adjuster}????????
 */
public Adjuster getAdjuster(){
  if (adjusterList.size() > SYSTEM_ADJUSTER_SIZE) {
    return adjusterList.get(adjusterList.size() - 1);
  }
  return null;
}
