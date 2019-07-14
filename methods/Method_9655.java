private void callAccepted(){
  endBtn.setVisibility(View.VISIBLE);
  if (VoIPService.getSharedInstance().hasEarpiece())   spkToggle.setVisibility(View.VISIBLE);
 else   spkToggle.setVisibility(View.GONE);
  bottomButtons.setVisibility(View.VISIBLE);
  if (didAcceptFromHere) {
    acceptBtn.setVisibility(View.GONE);
    ObjectAnimator colorAnim;
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
      colorAnim=ObjectAnimator.ofArgb(endBtnBg,"color",0xFF45bc4d,0xFFe61e44);
    }
 else {
      colorAnim=ObjectAnimator.ofInt(endBtnBg,"color",0xFF45bc4d,0xFFe61e44);
      colorAnim.setEvaluator(new ArgbEvaluator());
    }
    AnimatorSet set=new AnimatorSet();
    AnimatorSet decSet=new AnimatorSet();
    decSet.playTogether(ObjectAnimator.ofFloat(endBtnIcon,"rotation",-135,0),colorAnim);
    decSet.setInterpolator(CubicBezierInterpolator.EASE_OUT);
    decSet.setDuration(500);
    AnimatorSet accSet=new AnimatorSet();
    accSet.playTogether(ObjectAnimator.ofFloat(swipeViewsWrap,"alpha",1,0),ObjectAnimator.ofFloat(declineBtn,"alpha",0),ObjectAnimator.ofFloat(accountNameText,"alpha",0));
    accSet.setInterpolator(CubicBezierInterpolator.EASE_IN);
    accSet.setDuration(125);
    set.playTogether(decSet,accSet);
    set.addListener(new AnimatorListenerAdapter(){
      @Override public void onAnimationEnd(      Animator animation){
        swipeViewsWrap.setVisibility(View.GONE);
        declineBtn.setVisibility(View.GONE);
        accountNameText.setVisibility(View.GONE);
      }
    }
);
    set.start();
  }
 else {
    AnimatorSet set=new AnimatorSet();
    AnimatorSet decSet=new AnimatorSet();
    decSet.playTogether(ObjectAnimator.ofFloat(bottomButtons,"alpha",0,1));
    decSet.setInterpolator(CubicBezierInterpolator.EASE_OUT);
    decSet.setDuration(500);
    AnimatorSet accSet=new AnimatorSet();
    accSet.playTogether(ObjectAnimator.ofFloat(swipeViewsWrap,"alpha",1,0),ObjectAnimator.ofFloat(declineBtn,"alpha",0),ObjectAnimator.ofFloat(acceptBtn,"alpha",0),ObjectAnimator.ofFloat(accountNameText,"alpha",0));
    accSet.setInterpolator(CubicBezierInterpolator.EASE_IN);
    accSet.setDuration(125);
    set.playTogether(decSet,accSet);
    set.addListener(new AnimatorListenerAdapter(){
      @Override public void onAnimationEnd(      Animator animation){
        swipeViewsWrap.setVisibility(View.GONE);
        declineBtn.setVisibility(View.GONE);
        acceptBtn.setVisibility(View.GONE);
        accountNameText.setVisibility(View.GONE);
      }
    }
);
    set.start();
  }
}
