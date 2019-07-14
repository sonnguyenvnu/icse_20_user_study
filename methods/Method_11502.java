private void updateTabSelection(int position){
  for (int i=0; i < mTabCount; ++i) {
    View tabView=mTabsContainer.getChildAt(i);
    final boolean isSelect=i == position;
    TextView tab_title=(TextView)tabView.findViewById(R.id.tv_tab_title);
    if (tab_title != null) {
      tab_title.setTextColor(isSelect ? mTextSelectColor : mTextUnselectColor);
      if (mTextBold == TEXT_BOLD_WHEN_SELECT) {
        tab_title.getPaint().setFakeBoldText(isSelect);
      }
    }
  }
}
