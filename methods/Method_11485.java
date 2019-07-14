public TextView getTitleView(int tab){
  View tabView=mTabsContainer.getChildAt(tab);
  TextView tv_tab_title=(TextView)tabView.findViewById(R.id.tv_tab_title);
  return tv_tab_title;
}
