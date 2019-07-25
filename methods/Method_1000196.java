@NonNull public static ClearSmartPlaylistDialog create(AbsSmartPlaylist playlist){
  ClearSmartPlaylistDialog dialog=new ClearSmartPlaylistDialog();
  Bundle args=new Bundle();
  args.putParcelable("playlist",playlist);
  dialog.setArguments(args);
  return dialog;
}
