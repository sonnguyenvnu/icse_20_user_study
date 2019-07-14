public ConvenientBanner setPages(CBViewHolderCreator holderCreator,List<T> datas){
  this.mDatas=datas;
  pageAdapter=new CBPageAdapter(holderCreator,mDatas,canLoop);
  viewPager.setAdapter(pageAdapter);
  if (page_indicatorId != null)   setPageIndicator(page_indicatorId);
  cbLoopScaleHelper.setFirstItemPos(canLoop ? mDatas.size() : 0);
  cbLoopScaleHelper.attachToRecyclerView(viewPager);
  return this;
}
