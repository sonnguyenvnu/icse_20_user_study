private void toggleActionBar(boolean show,final boolean animated){
  if (show) {
    actionBar.setVisibility(View.VISIBLE);
  }
  actionBar.setEnabled(show);
  isActionBarVisible=show;
  if (animated) {
    ArrayList<Animator> arrayList=new ArrayList<>();
    arrayList.add(ObjectAnimator.ofFloat(actionBar,"alpha",show ? 1.0f : 0.0f));
    currentActionBarAnimation=new AnimatorSet();
    currentActionBarAnimation.playTogether(arrayList);
    if (!show) {
      currentActionBarAnimation.addListener(new AnimatorListenerAdapter(){
        @Override public void onAnimationEnd(        Animator animation){
          if (currentActionBarAnimation != null && currentActionBarAnimation.equals(animation)) {
            actionBar.setVisibility(View.GONE);
            currentActionBarAnimation=null;
          }
        }
      }
);
    }
    currentActionBarAnimation.setDuration(200);
    currentActionBarAnimation.start();
  }
 else {
    actionBar.setAlpha(show ? 1.0f : 0.0f);
    if (!show) {
      actionBar.setVisibility(View.GONE);
    }
  }
}
