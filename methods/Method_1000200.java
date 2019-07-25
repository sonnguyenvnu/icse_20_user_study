@NonNull public static RenamePlaylistDialog create(long playlistId){
  RenamePlaylistDialog dialog=new RenamePlaylistDialog();
  Bundle args=new Bundle();
  args.putLong(PLAYLIST_ID,playlistId);
  dialog.setArguments(args);
  return dialog;
}
