private void animateEnter(){
  ObjectAnimator animator=ObjectAnimator.ofInt(this,OFFSET,getHeight(),0);
  animator.setDuration(mShortAnimationTime);
  animator.setInterpolator(new LinearOutSlowInInterpolator());
  animator.addListener(new AnimatorListenerAdapter(){
    @Override public void onAnimationEnd(    Animator animation){
      if (mListener != null) {
        mListener.onEnterAnimationEnd();
      }
    }
  }
);
  animator.start();
}
