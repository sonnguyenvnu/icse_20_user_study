private void updateTabStyles(){
  for (int i=0; i < tabCount; i++) {
    View v=tabsContainer.getChildAt(i);
    v.setLayoutParams(defaultTabLayoutParams);
    if (shouldExpand) {
      v.setPadding(0,0,0,0);
      v.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT,1.0F));
    }
 else {
      v.setPadding(tabPadding,0,tabPadding,0);
    }
  }
}
