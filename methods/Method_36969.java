public int getNextItem(){
  if (pagerAdapter != null && pagerAdapter.getCount() != 0) {
    int next=super.getCurrentItem() + 1;
    return next % pagerAdapter.getRealCount();
  }
  return 0;
}
