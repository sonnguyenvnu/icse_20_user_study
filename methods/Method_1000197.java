@NonNull public static DeletePlaylistDialog create(List<Playlist> playlists){
  DeletePlaylistDialog dialog=new DeletePlaylistDialog();
  Bundle args=new Bundle();
  args.putParcelableArrayList("playlists",new ArrayList<>(playlists));
  dialog.setArguments(args);
  return dialog;
}
