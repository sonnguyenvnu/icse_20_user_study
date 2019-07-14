/** 
 * @return Whether has next song to play.<p/> If this query satisfies these conditions - comes from media player's complete listener - current play mode is PlayMode.LIST (the only limited play mode) - current song is already in the end of the list then there shouldn't be a next song to play, for this condition, it returns false. <p/> If this query is from user's action, such as from play controls, there should always has a next song to play, for this condition, it returns true.
 */
public boolean hasNext(boolean fromComplete){
  if (songs.isEmpty())   return false;
  if (fromComplete) {
    if (playMode == PlayMode.LIST && playingIndex + 1 >= songs.size())     return false;
  }
  return true;
}
