public void showRefreshing(){
  SectionsRecyclerView sectionsRecyclerView=mSectionsRecyclerView;
  if (sectionsRecyclerView == null || sectionsRecyclerView.isRefreshing()) {
    return;
  }
  ThreadUtils.assertMainThread();
  sectionsRecyclerView.setRefreshing(true);
}
