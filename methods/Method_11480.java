private void updateTabSelection(int position){
  for (int i=0; i < mTabCount; ++i) {
    View tabView=mTabsContainer.getChildAt(i);
    final boolean isSelect=i == position;
    TextView tab_title=(TextView)tabView.findViewById(R.id.tv_tab_title);
    tab_title.setTextColor(isSelect ? mTextSelectColor : mTextUnselectColor);
    ImageView iv_tab_icon=(ImageView)tabView.findViewById(R.id.iv_tab_icon);
    CustomTabEntity tabEntity=mTabEntitys.get(i);
    iv_tab_icon.setImageResource(isSelect ? tabEntity.getTabSelectedIcon() : tabEntity.getTabUnselectedIcon());
    if (mTextBold == TEXT_BOLD_WHEN_SELECT) {
      tab_title.getPaint().setFakeBoldText(isSelect);
    }
  }
}
