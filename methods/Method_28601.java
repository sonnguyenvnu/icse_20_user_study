/** 
 * ???????
 */
private void initWidth(){
  mRecyclerView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener(){
    @Override public void onGlobalLayout(){
      mRecyclerView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
      scrollToPosition(mFirstItemPos);
    }
  }
);
}
