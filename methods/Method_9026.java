public void addTextTab(final int id,CharSequence text){
  int position=tabCount++;
  if (position == 0 && selectedTabId == -1) {
    selectedTabId=id;
  }
  positionToId.put(position,id);
  idToPosition.put(id,position);
  if (selectedTabId != -1 && selectedTabId == id) {
    currentPosition=position;
    prevLayoutWidth=0;
  }
  TextView tab=new TextView(getContext());
  tab.setGravity(Gravity.CENTER);
  tab.setText(text);
  tab.setBackgroundDrawable(Theme.createSelectorDrawable(Theme.getColor(Theme.key_actionBarTabSelector),3));
  tab.setTextSize(TypedValue.COMPLEX_UNIT_DIP,14);
  tab.setSingleLine(true);
  tab.setTypeface(AndroidUtilities.getTypeface("fonts/rmedium.ttf"));
  tab.setPadding(AndroidUtilities.dp(8),0,AndroidUtilities.dp(8),0);
  tab.setOnClickListener(v -> {
    int position1=tabsContainer.indexOfChild(v);
    if (position1 < 0 || position1 == currentPosition) {
      return;
    }
    boolean scrollingForward=currentPosition < position1;
    previousPosition=currentPosition;
    currentPosition=position1;
    selectedTabId=id;
    if (animatingIndicator) {
      AndroidUtilities.cancelRunOnUIThread(animationRunnable);
      animatingIndicator=false;
    }
    animationTime=0;
    animatingIndicator=true;
    animateIndicatorStartX=indicatorX;
    animateIndicatorStartWidth=indicatorWidth;
    TextView nextChild=(TextView)v;
    animateIndicatorToWidth=getChildWidth(nextChild);
    animateIndicatorToX=nextChild.getLeft() + (nextChild.getMeasuredWidth() - animateIndicatorToWidth) / 2;
    setEnabled(false);
    AndroidUtilities.runOnUIThread(animationRunnable,16);
    if (delegate != null) {
      delegate.onPageSelected(id,scrollingForward);
    }
    scrollToChild(position1);
  }
);
  int tabWidth=(int)Math.ceil(tab.getPaint().measureText(text,0,text.length())) + AndroidUtilities.dp(16);
  allTextWidth+=tabWidth;
  positionToWidth.put(position,tabWidth);
  tabsContainer.addView(tab,LayoutHelper.createLinear(0,LayoutHelper.MATCH_PARENT));
}
