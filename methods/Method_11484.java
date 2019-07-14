public ImageView getIconView(int tab){
  View tabView=mTabsContainer.getChildAt(tab);
  ImageView iv_tab_icon=(ImageView)tabView.findViewById(R.id.iv_tab_icon);
  return iv_tab_icon;
}
