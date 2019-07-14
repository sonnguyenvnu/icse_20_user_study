public static ObjectAnimator popout(final View view,final long duration,final AnimatorListenerAdapter animatorListenerAdapter){
  ObjectAnimator popout=ObjectAnimator.ofPropertyValuesHolder(view,PropertyValuesHolder.ofFloat("alpha",1f,0f),PropertyValuesHolder.ofFloat("scaleX",1f,0f),PropertyValuesHolder.ofFloat("scaleY",1f,0f));
  popout.setDuration(duration);
  popout.addListener(new AnimatorListenerAdapter(){
    @Override public void onAnimationEnd(    Animator animation){
      super.onAnimationEnd(animation);
      view.setVisibility(View.GONE);
      if (animatorListenerAdapter != null) {
        animatorListenerAdapter.onAnimationEnd(animation);
      }
    }
  }
);
  popout.setInterpolator(new AnticipateOvershootInterpolator());
  return popout;
}
