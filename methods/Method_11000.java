@Override public void scrollViewTo(int x,int y){
  x=clamp(x,mRxCardStackView.getWidth() - mRxCardStackView.getPaddingRight() - mRxCardStackView.getPaddingLeft(),mRxCardStackView.getWidth());
  y=clamp(y,mRxCardStackView.getShowHeight(),mRxCardStackView.getTotalLength());
  mScrollY=y;
  mScrollX=x;
  updateChildPos();
}
