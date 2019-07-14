/** 
 * ????Adjuster? ?????????3?Adjuster?????Adjuster?????????Adjuster?
 * @param adjuster {@link Adjuster}?????????
 * @return SuperTextView
 */
public SuperTextView addAdjuster(Adjuster adjuster){
  if (adjusterList.size() < SYSTEM_ADJUSTER_SIZE + ALLOW_CUSTOM_ADJUSTER_SIZE) {
    innerAddAdjuster(adjuster);
  }
 else {
    removeAdjuster(adjusterList.size() - 1);
    innerAddAdjuster(adjuster);
  }
  return this;
}
