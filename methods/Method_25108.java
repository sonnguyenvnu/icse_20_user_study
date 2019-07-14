private void createCustomAnimation(){
  AnimatorSet set=new AnimatorSet();
  ObjectAnimator scaleOutX=ObjectAnimator.ofFloat(menuGreen.getMenuIconView(),"scaleX",1.0f,0.2f);
  ObjectAnimator scaleOutY=ObjectAnimator.ofFloat(menuGreen.getMenuIconView(),"scaleY",1.0f,0.2f);
  ObjectAnimator scaleInX=ObjectAnimator.ofFloat(menuGreen.getMenuIconView(),"scaleX",0.2f,1.0f);
  ObjectAnimator scaleInY=ObjectAnimator.ofFloat(menuGreen.getMenuIconView(),"scaleY",0.2f,1.0f);
  scaleOutX.setDuration(50);
  scaleOutY.setDuration(50);
  scaleInX.setDuration(150);
  scaleInY.setDuration(150);
  scaleInX.addListener(new AnimatorListenerAdapter(){
    @Override public void onAnimationStart(    Animator animation){
      menuGreen.getMenuIconView().setImageResource(menuGreen.isOpened() ? R.drawable.ic_close : R.drawable.ic_star);
    }
  }
);
  set.play(scaleOutX).with(scaleOutY);
  set.play(scaleInX).with(scaleInY).after(scaleOutX);
  set.setInterpolator(new OvershootInterpolator(2));
  menuGreen.setIconToggleAnimatorSet(set);
}
