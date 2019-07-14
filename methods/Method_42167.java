@Override public void onItemSelected(int position){
  if (listener != null)   listener.onMediaClick(RvMediaFragment.this.album,adapter.getMedia(),position);
}
