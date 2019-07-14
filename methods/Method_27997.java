@Override public void onAppenedtab(@Nullable RepoFile repoFile){
  if (repoFile != null) {
    adapter.addItem(repoFile);
    recycler.scrollToPosition(adapter.getItemCount() - 1);
  }
}
