private void updateSearchInterface(){
  if (adapter != null) {
    adapter.notifyDataSetChanged();
  }
}
