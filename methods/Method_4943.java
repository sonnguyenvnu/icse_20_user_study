/** 
 * Returns whether  {@code periodHolder} can be kept for playing the media period described by{@code info}.
 */
private boolean canKeepMediaPeriodHolder(MediaPeriodHolder periodHolder,MediaPeriodInfo info){
  MediaPeriodInfo periodHolderInfo=periodHolder.info;
  return periodHolderInfo.startPositionUs == info.startPositionUs && periodHolderInfo.id.equals(info.id);
}
