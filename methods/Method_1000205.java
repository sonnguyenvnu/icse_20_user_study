public static boolean enqueue(Song song){
  if (musicService != null) {
    if (getPlayingQueue().size() > 0) {
      musicService.addSong(song);
    }
 else {
      List<Song> queue=new ArrayList<>();
      queue.add(song);
      openQueue(queue,0,false);
    }
    Toast.makeText(musicService,musicService.getResources().getString(R.string.added_title_to_playing_queue),Toast.LENGTH_SHORT).show();
    return true;
  }
  return false;
}
