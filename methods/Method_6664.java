private void buildShuffledPlayList(){
  if (playlist.isEmpty()) {
    return;
  }
  ArrayList<MessageObject> all=new ArrayList<>(playlist);
  shuffledPlaylist.clear();
  MessageObject messageObject=playlist.get(currentPlaylistNum);
  all.remove(currentPlaylistNum);
  shuffledPlaylist.add(messageObject);
  int count=all.size();
  for (int a=0; a < count; a++) {
    int index=Utilities.random.nextInt(all.size());
    shuffledPlaylist.add(all.get(index));
    all.remove(index);
  }
}
