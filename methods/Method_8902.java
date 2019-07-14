private void closeStickersView(){
  if (stickersView == null || stickersView.getVisibility() != VISIBLE) {
    return;
  }
  pickingSticker=false;
  Animator a=ObjectAnimator.ofFloat(stickersView,"alpha",1.0f,0.0f);
  a.setDuration(200);
  a.addListener(new AnimatorListenerAdapter(){
    @Override public void onAnimationEnd(    Animator animator){
      stickersView.setVisibility(GONE);
    }
  }
);
  a.start();
}
