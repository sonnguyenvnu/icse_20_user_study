/** 
 * Returns the index of the window before the window at index  {@code windowIndex} depending on the{@code repeatMode} and whether shuffling is enabled.
 * @param windowIndex Index of a window in the timeline.
 * @param repeatMode A repeat mode.
 * @param shuffleModeEnabled Whether shuffling is enabled.
 * @return The index of the previous window, or {@link C#INDEX_UNSET} if this is the first window.
 */
public int getPreviousWindowIndex(int windowIndex,@Player.RepeatMode int repeatMode,boolean shuffleModeEnabled){
switch (repeatMode) {
case Player.REPEAT_MODE_OFF:
    return windowIndex == getFirstWindowIndex(shuffleModeEnabled) ? C.INDEX_UNSET : windowIndex - 1;
case Player.REPEAT_MODE_ONE:
  return windowIndex;
case Player.REPEAT_MODE_ALL:
return windowIndex == getFirstWindowIndex(shuffleModeEnabled) ? getLastWindowIndex(shuffleModeEnabled) : windowIndex - 1;
default :
throw new IllegalStateException();
}
}
