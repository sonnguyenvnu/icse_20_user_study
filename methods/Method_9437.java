private void showEditDoneProgress(final boolean animateDoneItem,final boolean show){
  if (doneItemAnimation != null) {
    doneItemAnimation.cancel();
  }
  if (animateDoneItem && doneItem != null) {
    doneItemAnimation=new AnimatorSet();
    if (show) {
      progressView.setVisibility(View.VISIBLE);
      doneItem.setEnabled(false);
      doneItemAnimation.playTogether(ObjectAnimator.ofFloat(doneItem.getImageView(),"scaleX",0.1f),ObjectAnimator.ofFloat(doneItem.getImageView(),"scaleY",0.1f),ObjectAnimator.ofFloat(doneItem.getImageView(),"alpha",0.0f),ObjectAnimator.ofFloat(progressView,"scaleX",1.0f),ObjectAnimator.ofFloat(progressView,"scaleY",1.0f),ObjectAnimator.ofFloat(progressView,"alpha",1.0f));
    }
 else {
      if (webView != null) {
        doneItemAnimation.playTogether(ObjectAnimator.ofFloat(progressView,"scaleX",0.1f),ObjectAnimator.ofFloat(progressView,"scaleY",0.1f),ObjectAnimator.ofFloat(progressView,"alpha",0.0f));
      }
 else {
        doneItem.getImageView().setVisibility(View.VISIBLE);
        doneItem.setEnabled(true);
        doneItemAnimation.playTogether(ObjectAnimator.ofFloat(progressView,"scaleX",0.1f),ObjectAnimator.ofFloat(progressView,"scaleY",0.1f),ObjectAnimator.ofFloat(progressView,"alpha",0.0f),ObjectAnimator.ofFloat(doneItem.getImageView(),"scaleX",1.0f),ObjectAnimator.ofFloat(doneItem.getImageView(),"scaleY",1.0f),ObjectAnimator.ofFloat(doneItem.getImageView(),"alpha",1.0f));
      }
    }
    doneItemAnimation.addListener(new AnimatorListenerAdapter(){
      @Override public void onAnimationEnd(      Animator animation){
        if (doneItemAnimation != null && doneItemAnimation.equals(animation)) {
          if (!show) {
            progressView.setVisibility(View.INVISIBLE);
          }
 else {
            doneItem.getImageView().setVisibility(View.INVISIBLE);
          }
        }
      }
      @Override public void onAnimationCancel(      Animator animation){
        if (doneItemAnimation != null && doneItemAnimation.equals(animation)) {
          doneItemAnimation=null;
        }
      }
    }
);
    doneItemAnimation.setDuration(150);
    doneItemAnimation.start();
  }
 else   if (payTextView != null) {
    doneItemAnimation=new AnimatorSet();
    if (show) {
      progressViewButton.setVisibility(View.VISIBLE);
      bottomLayout.setEnabled(false);
      doneItemAnimation.playTogether(ObjectAnimator.ofFloat(payTextView,"scaleX",0.1f),ObjectAnimator.ofFloat(payTextView,"scaleY",0.1f),ObjectAnimator.ofFloat(payTextView,"alpha",0.0f),ObjectAnimator.ofFloat(progressViewButton,"scaleX",1.0f),ObjectAnimator.ofFloat(progressViewButton,"scaleY",1.0f),ObjectAnimator.ofFloat(progressViewButton,"alpha",1.0f));
    }
 else {
      payTextView.setVisibility(View.VISIBLE);
      bottomLayout.setEnabled(true);
      doneItemAnimation.playTogether(ObjectAnimator.ofFloat(progressViewButton,"scaleX",0.1f),ObjectAnimator.ofFloat(progressViewButton,"scaleY",0.1f),ObjectAnimator.ofFloat(progressViewButton,"alpha",0.0f),ObjectAnimator.ofFloat(payTextView,"scaleX",1.0f),ObjectAnimator.ofFloat(payTextView,"scaleY",1.0f),ObjectAnimator.ofFloat(payTextView,"alpha",1.0f));
    }
    doneItemAnimation.addListener(new AnimatorListenerAdapter(){
      @Override public void onAnimationEnd(      Animator animation){
        if (doneItemAnimation != null && doneItemAnimation.equals(animation)) {
          if (!show) {
            progressViewButton.setVisibility(View.INVISIBLE);
          }
 else {
            payTextView.setVisibility(View.INVISIBLE);
          }
        }
      }
      @Override public void onAnimationCancel(      Animator animation){
        if (doneItemAnimation != null && doneItemAnimation.equals(animation)) {
          doneItemAnimation=null;
        }
      }
    }
);
    doneItemAnimation.setDuration(150);
    doneItemAnimation.start();
  }
}
