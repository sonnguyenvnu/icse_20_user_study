/** 
 * The current song being played or is playing based on the  {@link #playingIndex}
 */
public Song getCurrentSong(){
  if (playingIndex != NO_POSITION) {
    return songs.get(playingIndex);
  }
  return null;
}
