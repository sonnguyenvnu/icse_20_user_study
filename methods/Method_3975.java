final void fillRemainingScrollValues(State state){
  if (getScrollState() == SCROLL_STATE_SETTLING) {
    final OverScroller scroller=mViewFlinger.mOverScroller;
    state.mRemainingScrollHorizontal=scroller.getFinalX() - scroller.getCurrX();
    state.mRemainingScrollVertical=scroller.getFinalY() - scroller.getCurrY();
  }
 else {
    state.mRemainingScrollHorizontal=0;
    state.mRemainingScrollVertical=0;
  }
}
