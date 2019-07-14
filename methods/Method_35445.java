@Override public void onAddPlayList(){
  EditPlayListDialogFragment.createPlayList().setCallback(PlayListFragment.this).show(getFragmentManager().beginTransaction(),"CreatePlayList");
}
