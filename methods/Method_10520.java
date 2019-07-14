@Override public void add(View view,int position){
  int[] addLocation=new int[2];
  int[] cartLocation=new int[2];
  int[] recycleLocation=new int[2];
  view.getLocationInWindow(addLocation);
  mShoppingCart.getLocationInWindow(cartLocation);
  mRightMenu.getLocationInWindow(recycleLocation);
  PointF startP=new PointF();
  PointF endP=new PointF();
  PointF controlP=new PointF();
  startP.x=addLocation[0];
  startP.y=addLocation[1] - recycleLocation[1];
  endP.x=cartLocation[0];
  endP.y=cartLocation[1] - recycleLocation[1];
  controlP.x=endP.x;
  controlP.y=startP.y;
  final RxFakeAddImageView rxFakeAddImageView=new RxFakeAddImageView(this);
  mMainLayout.addView(rxFakeAddImageView);
  rxFakeAddImageView.setImageResource(R.drawable.ic_add_circle_blue_700_36dp);
  rxFakeAddImageView.getLayoutParams().width=getResources().getDimensionPixelSize(R.dimen.item_dish_circle_size);
  rxFakeAddImageView.getLayoutParams().height=getResources().getDimensionPixelSize(R.dimen.item_dish_circle_size);
  rxFakeAddImageView.setVisibility(View.VISIBLE);
  ObjectAnimator addAnimator=ObjectAnimator.ofObject(rxFakeAddImageView,"mPointF",new RxPointFTypeEvaluator(controlP),startP,endP);
  addAnimator.setInterpolator(new AccelerateInterpolator());
  addAnimator.addListener(new Animator.AnimatorListener(){
    @Override public void onAnimationStart(    Animator animator){
      rxFakeAddImageView.setVisibility(View.VISIBLE);
    }
    @Override public void onAnimationEnd(    Animator animator){
      rxFakeAddImageView.setVisibility(View.GONE);
      mMainLayout.removeView(rxFakeAddImageView);
    }
    @Override public void onAnimationCancel(    Animator animator){
    }
    @Override public void onAnimationRepeat(    Animator animator){
    }
  }
);
  ObjectAnimator scaleAnimatorX=new ObjectAnimator().ofFloat(mShoppingCart,"scaleX",0.6f,1.0f);
  ObjectAnimator scaleAnimatorY=new ObjectAnimator().ofFloat(mShoppingCart,"scaleY",0.6f,1.0f);
  scaleAnimatorX.setInterpolator(new AccelerateInterpolator());
  scaleAnimatorY.setInterpolator(new AccelerateInterpolator());
  AnimatorSet animatorSet=new AnimatorSet();
  animatorSet.play(scaleAnimatorX).with(scaleAnimatorY).after(addAnimator);
  animatorSet.setDuration(800);
  animatorSet.start();
  showTotalPrice();
}
