private void addFolder(final File dir){
  String[] list=dir.list(new ImageFileFilter(true));
  if (list != null && list.length > 0) {
    scanFile(getApplicationContext(),list);
    HandlingAlbums.getInstance(getApplicationContext()).addFolderToWhiteList(dir.getPath());
    folders.add(0,dir.getPath());
    adapter.notifyItemInserted(0);
    checkNothing();
  }
 else {
    Toast.makeText(getApplicationContext(),R.string.no_media_in_this_folder,Toast.LENGTH_SHORT).show();
  }
}
