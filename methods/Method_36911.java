/** 
 * {@inheritDoc}
 */
@Override public void refresh(final boolean layoutUpdated){
  final RecyclerView contentView=getContentView();
  if (contentView == null) {
    return;
  }
  if (contentView.getScrollState() != RecyclerView.SCROLL_STATE_IDLE) {
  }
  updateRunnable=new Runnable(){
    @Override public void run(){
      if (!contentView.isComputingLayout()) {
        mGroupBasicAdapter.notifyUpdate(layoutUpdated);
        if (mSwipeItemTouchListener != null) {
          mSwipeItemTouchListener.updateCurrCard();
        }
      }
    }
  }
;
  contentView.post(updateRunnable);
}
