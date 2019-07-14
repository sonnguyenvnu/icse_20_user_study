@Override public void postBindView(BaseCell cell){
  getContext().registerReceiver(mScreenBroadcastReceiver,filter);
  BannerCell bannerCell=(BannerCell)cell;
  bannerCell.initAdapter();
  if (cell.style != null) {
    setPadding(cell.style.padding[3],cell.style.padding[0],cell.style.padding[1],cell.style.padding[2]);
  }
  setBackgroundColor(bannerCell.mBgColor);
  setAdapter(bannerCell.mBannerWrapper);
  mUltraViewPager.setAutoMeasureHeight(true);
  this.ratio=bannerCell.mRatio;
  this.height=bannerCell.height;
  mUltraViewPager.setRatio(this.ratio);
  setAutoScroll(bannerCell.mAutoScrollInternal,bannerCell.mSpecialInterval);
  mUltraViewPager.setPageMargin(bannerCell.hGap);
  if (bannerCell.mCells.size() <= bannerCell.mInfiniteMinCount) {
    setInfiniteLoop(false);
  }
 else {
    setInfiniteLoop(bannerCell.mInfinite);
  }
  setIndicatorGravity(getIndicatorGravity(bannerCell.mIndicatorGravity));
  setIndicatorPos(bannerCell.mIndicatorPos);
  int indicatorGap=bannerCell.mIndicatorGap;
  if (indicatorGap < 0) {
    indicatorGap=mIndicatorGap;
  }
  setIndicatorGap(indicatorGap);
  int indicatorMargin=bannerCell.mIndicatorMargin;
  if (indicatorMargin <= 0) {
    indicatorMargin=mIndicatorMargin;
  }
  setIndicatorMargin(indicatorMargin);
  int indicatorHeight=bannerCell.mIndicatorHeight;
  setIndicatorHeight(indicatorHeight);
  if (bannerCell.itemMargin[0] > 0 || bannerCell.itemMargin[1] > 0) {
    setScrollMargin(bannerCell.itemMargin[0],bannerCell.itemMargin[1]);
    mUltraViewPager.setClipToPadding(false);
    mUltraViewPager.setClipChildren(false);
  }
 else {
    setScrollMargin(0,0);
    mUltraViewPager.setClipToPadding(true);
    mUltraViewPager.setClipChildren(true);
  }
  VirtualLayoutManager.LayoutParams layoutParams=(VirtualLayoutManager.LayoutParams)getLayoutParams();
  layoutParams.setMargins(bannerCell.margin[3],bannerCell.margin[0],bannerCell.margin[1],bannerCell.margin[2]);
  mUltraViewPager.setItemRatio(bannerCell.itemRatio);
  currentItemPos=bannerCell.extras.getIntValue(CURRENT_POS);
  mUltraViewPager.setCurrentItem(currentItemPos);
  updateIndicators(bannerCell.mIndicatorFocus,bannerCell.mIndicatorNor,bannerCell.mIndicatorRadius,bannerCell.mIndicatorColor,bannerCell.mIndicatorDefaultColor);
  recycleView();
  bindHeaderView(bannerCell.mHeader);
  bindFooterView(bannerCell.mFooter);
  if (cell.serviceManager != null) {
    bannerSupport=cell.serviceManager.getService(BannerSupport.class);
  }
}
