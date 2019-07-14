public void addHeart(int color){
  RxHeartView rxHeartView=new RxHeartView(getContext());
  rxHeartView.setColor(color);
  mAnimator.start(rxHeartView,this);
}
