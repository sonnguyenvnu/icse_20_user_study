public void addSong(@Nullable List<Song> songs,int index){
  if (songs == null || songs.isEmpty())   return;
  this.songs.addAll(index,songs);
  this.numOfSongs=this.songs.size();
}
