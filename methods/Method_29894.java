@Override protected RecyclerView.LayoutManager onCreateLayoutManager(){
  return new FastSmoothScrollStaggeredGridLayoutManager(CardUtils.getColumnCount(getActivity()),StaggeredGridLayoutManager.VERTICAL);
}
