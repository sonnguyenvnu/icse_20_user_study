@NonNull public static AddToPlaylistDialog create(Song song){
  List<Song> list=new ArrayList<>();
  list.add(song);
  return create(list);
}
