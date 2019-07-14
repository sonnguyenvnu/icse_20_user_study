public void addHeart(int color,int heartResId,int heartBorderResId){
  RxHeartView rxHeartView=new RxHeartView(getContext());
  rxHeartView.setColorAndDrawables(color,heartResId,heartBorderResId);
  mAnimator.start(rxHeartView,this);
}
