/** 
 * Move the playingIndex forward depends on the play mode
 * @return The next song to play
 */
public Song next(){
switch (playMode) {
case LOOP:
case LIST:
case SINGLE:
    int newIndex=playingIndex + 1;
  if (newIndex >= songs.size()) {
    newIndex=0;
  }
playingIndex=newIndex;
break;
case SHUFFLE:
playingIndex=randomPlayIndex();
break;
}
return songs.get(playingIndex);
}
