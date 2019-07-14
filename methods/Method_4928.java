/** 
 * Returns a copy of this instance with the start position set to the specified value. 
 */
public MediaPeriodInfo copyWithStartPositionUs(long startPositionUs){
  return new MediaPeriodInfo(id,startPositionUs,contentPositionUs,durationUs,isLastInTimelinePeriod,isFinal);
}
