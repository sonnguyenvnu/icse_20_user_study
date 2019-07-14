public void setData(List<BaseCell> cells){
  initAdapter();
  this.mCells.clear();
  this.mCells.addAll(cells);
  mBannerAdapter.notifyDataSetChanged();
}
