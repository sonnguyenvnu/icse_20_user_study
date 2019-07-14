/** 
 * Prepare to play
 */
public boolean prepare(){
  if (songs.isEmpty())   return false;
  if (playingIndex == NO_POSITION) {
    playingIndex=0;
  }
  return true;
}
