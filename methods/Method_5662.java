/** 
 * Converts (windowIndex, windowPositionUs) to the corresponding (periodUid, periodPositionUs).
 * @param window A {@link Window} that may be overwritten.
 * @param period A {@link Period} that may be overwritten.
 * @param windowIndex The window index.
 * @param windowPositionUs The window time, or {@link C#TIME_UNSET} to use the window's defaultstart position.
 * @param defaultPositionProjectionUs If {@code windowPositionUs} is {@link C#TIME_UNSET}, the duration into the future by which the window's position should be projected.
 * @return The corresponding (periodUid, periodPositionUs), or null if {@code #windowPositionUs}is  {@link C#TIME_UNSET},  {@code defaultPositionProjectionUs} is non-zero, and the window'sposition could not be projected by  {@code defaultPositionProjectionUs}.
 */
@Nullable public final Pair<Object,Long> getPeriodPosition(Window window,Period period,int windowIndex,long windowPositionUs,long defaultPositionProjectionUs){
  Assertions.checkIndex(windowIndex,0,getWindowCount());
  getWindow(windowIndex,window,false,defaultPositionProjectionUs);
  if (windowPositionUs == C.TIME_UNSET) {
    windowPositionUs=window.getDefaultPositionUs();
    if (windowPositionUs == C.TIME_UNSET) {
      return null;
    }
  }
  int periodIndex=window.firstPeriodIndex;
  long periodPositionUs=window.getPositionInFirstPeriodUs() + windowPositionUs;
  long periodDurationUs=getPeriod(periodIndex,period,true).getDurationUs();
  while (periodDurationUs != C.TIME_UNSET && periodPositionUs >= periodDurationUs && periodIndex < window.lastPeriodIndex) {
    periodPositionUs-=periodDurationUs;
    periodDurationUs=getPeriod(++periodIndex,period,true).getDurationUs();
  }
  return Pair.create(Assertions.checkNotNull(period.uid),periodPositionUs);
}
