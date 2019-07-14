/** 
 * Returns the index of the period after the period at index  {@code periodIndex} depending on the{@code repeatMode} and whether shuffling is enabled.
 * @param periodIndex Index of a period in the timeline.
 * @param period A {@link Period} to be used internally. Must not be null.
 * @param window A {@link Window} to be used internally. Must not be null.
 * @param repeatMode A repeat mode.
 * @param shuffleModeEnabled Whether shuffling is enabled.
 * @return The index of the next period, or {@link C#INDEX_UNSET} if this is the last period.
 */
public final int getNextPeriodIndex(int periodIndex,Period period,Window window,@Player.RepeatMode int repeatMode,boolean shuffleModeEnabled){
  int windowIndex=getPeriod(periodIndex,period).windowIndex;
  if (getWindow(windowIndex,window).lastPeriodIndex == periodIndex) {
    int nextWindowIndex=getNextWindowIndex(windowIndex,repeatMode,shuffleModeEnabled);
    if (nextWindowIndex == C.INDEX_UNSET) {
      return C.INDEX_UNSET;
    }
    return getWindow(nextWindowIndex,window).firstPeriodIndex;
  }
  return periodIndex + 1;
}
