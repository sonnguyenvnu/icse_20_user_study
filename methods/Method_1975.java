private void updateAdapter(List<String> urls){
  if (mCurrentAdapter != null) {
    mCurrentAdapter.clear();
    if (urls != null) {
      for (      String url : urls) {
        mCurrentAdapter.addUrl(url);
      }
    }
    mCurrentAdapter.notifyDataSetChanged();
  }
}
