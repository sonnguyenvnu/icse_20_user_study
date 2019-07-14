public Song last(){
switch (playMode) {
case LOOP:
case LIST:
case SINGLE:
    int newIndex=playingIndex - 1;
  if (newIndex < 0) {
    newIndex=songs.size() - 1;
  }
playingIndex=newIndex;
break;
case SHUFFLE:
playingIndex=randomPlayIndex();
break;
}
return songs.get(playingIndex);
}
