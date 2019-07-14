/** 
 * Returns whether or not this cue should be placed in the default position and rolled-up with the other "normal" cues.
 * @return Whether this cue should be placed in the default position.
 */
public boolean isNormalCue(){
  return (line == DIMEN_UNSET && position == DIMEN_UNSET);
}
