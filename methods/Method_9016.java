public void updateTabStyles(){
  for (int i=0; i < tabCount; i++) {
    View v=tabsContainer.getChildAt(i);
    if (shouldExpand) {
      v.setLayoutParams(defaultExpandLayoutParams);
    }
 else {
      v.setLayoutParams(defaultTabLayoutParams);
    }
  }
}
