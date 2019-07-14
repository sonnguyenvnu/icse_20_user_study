private void showEditDoneProgress(final boolean show){
  if (doneItemAnimation != null) {
    doneItemAnimation.cancel();
  }
  doneItemAnimation=new AnimatorSet();
  if (show) {
    doneProgressView.setTag(1);
    doneProgressView.setVisibility(View.VISIBLE);
    doneItemAnimation.playTogether(ObjectAnimator.ofFloat(doneItem.getImageView(),"scaleX",0.1f),ObjectAnimator.ofFloat(doneItem.getImageView(),"scaleY",0.1f),ObjectAnimator.ofFloat(doneItem.getImageView(),"alpha",0.0f),ObjectAnimator.ofFloat(doneProgressView,"scaleX",1.0f),ObjectAnimator.ofFloat(doneProgressView,"scaleY",1.0f),ObjectAnimator.ofFloat(doneProgressView,"alpha",1.0f));
  }
 else {
    doneProgressView.setTag(null);
    doneItem.getImageView().setVisibility(View.VISIBLE);
    doneItemAnimation.playTogether(ObjectAnimator.ofFloat(doneProgressView,"scaleX",0.1f),ObjectAnimator.ofFloat(doneProgressView,"scaleY",0.1f),ObjectAnimator.ofFloat(doneProgressView,"alpha",0.0f),ObjectAnimator.ofFloat(doneItem.getImageView(),"scaleX",1.0f),ObjectAnimator.ofFloat(doneItem.getImageView(),"scaleY",1.0f),ObjectAnimator.ofFloat(doneItem.getImageView(),"alpha",1.0f));
  }
  doneItemAnimation.addListener(new AnimatorListenerAdapter(){
    @Override public void onAnimationEnd(    Animator animation){
      if (doneItemAnimation != null && doneItemAnimation.equals(animation)) {
        if (!show) {
          doneProgressView.setVisibility(View.INVISIBLE);
        }
 else {
          doneItem.getImageView().setVisibility(View.INVISIBLE);
        }
      }
    }
    @Override public void onAnimationCancel(    Animator animation){
      if (doneItemAnimation != null && doneItemAnimation.equals(animation)) {
        doneItemAnimation=null;
      }
    }
  }
);
  doneItemAnimation.setDuration(150);
  doneItemAnimation.start();
}
