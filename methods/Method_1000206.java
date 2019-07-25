public static boolean enqueue(@NonNull List<Song> songs){
  if (musicService != null) {
    if (getPlayingQueue().size() > 0) {
      musicService.addSongs(songs);
    }
 else {
      openQueue(songs,0,false);
    }
    final String toast=songs.size() == 1 ? musicService.getResources().getString(R.string.added_title_to_playing_queue) : musicService.getResources().getString(R.string.added_x_titles_to_playing_queue,songs.size());
    Toast.makeText(musicService,toast,Toast.LENGTH_SHORT).show();
    return true;
  }
  return false;
}
