@Override public void onSelectMode(boolean selectMode){
  refresh.setEnabled(!selectMode);
  updateToolbar();
  getActivity().invalidateOptionsMenu();
}
