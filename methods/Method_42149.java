@Override public void onItemSelected(int position){
  if (listener != null)   listener.onAlbumClick(adapter.get(position));
}
