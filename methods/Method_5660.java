/** 
 * Returns whether the given period is the last period of the timeline depending on the {@code repeatMode} and whether shuffling is enabled.
 * @param periodIndex A period index.
 * @param period A {@link Period} to be used internally. Must not be null.
 * @param window A {@link Window} to be used internally. Must not be null.
 * @param repeatMode A repeat mode.
 * @param shuffleModeEnabled Whether shuffling is enabled.
 * @return Whether the period of the given index is the last period of the timeline.
 */
public final boolean isLastPeriod(int periodIndex,Period period,Window window,@Player.RepeatMode int repeatMode,boolean shuffleModeEnabled){
  return getNextPeriodIndex(periodIndex,period,window,repeatMode,shuffleModeEnabled) == C.INDEX_UNSET;
}
