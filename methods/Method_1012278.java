/** 
 * ????
 * @param width     ?????
 * @param maxHeight ???????
 * @return
 */
protected T create(int width,int maxHeight){
  int margin=DensityUtils.dp2px(getContext(),5);
  if (maxHeight != 0) {
    mListView=new XUIWrapContentListView(getContext(),maxHeight);
    FrameLayout.LayoutParams lp=new FrameLayout.LayoutParams(width,maxHeight);
    lp.setMargins(0,margin,0,margin);
    mListView.setLayoutParams(lp);
  }
 else {
    mListView=new XUIWrapContentListView(getContext());
    FrameLayout.LayoutParams lp=new FrameLayout.LayoutParams(width,FrameLayout.LayoutParams.WRAP_CONTENT);
    lp.setMargins(0,margin,0,margin);
    mListView.setLayoutParams(lp);
  }
  mListView.setPadding(margin,0,margin,0);
  mListView.setAdapter(mAdapter);
  mListView.setVerticalScrollBarEnabled(false);
  updateListViewDivider(mListView);
  setContentView(mListView);
  return (T)this;
}
