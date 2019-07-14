public void notifyDataSetChanged(){
  tabsContainer.removeAllViews();
  tabCount=pager.getAdapter().getCount();
  for (int i=0; i < tabCount; i++) {
    if (pager.getAdapter() instanceof IconTabProvider) {
      addIconTab(i,((IconTabProvider)pager.getAdapter()).getPageIconDrawable(i),pager.getAdapter().getPageTitle(i));
    }
  }
  updateTabStyles();
  getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener(){
    @Override public void onGlobalLayout(){
      getViewTreeObserver().removeOnGlobalLayoutListener(this);
      currentPosition=pager.getCurrentItem();
      scrollToChild(currentPosition,0);
    }
  }
);
}
