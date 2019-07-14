private void toggleActionBar(boolean show,final boolean animated){
  if (show) {
    actionBar.setVisibility(View.VISIBLE);
    if (videoPlayer != null) {
      bottomLayout.setVisibility(View.VISIBLE);
    }
    if (captionTextView.getTag() != null) {
      captionTextView.setVisibility(View.VISIBLE);
    }
  }
  isActionBarVisible=show;
  actionBar.setEnabled(show);
  bottomLayout.setEnabled(show);
  if (animated) {
    ArrayList<Animator> arrayList=new ArrayList<>();
    arrayList.add(ObjectAnimator.ofFloat(actionBar,View.ALPHA,show ? 1.0f : 0.0f));
    arrayList.add(ObjectAnimator.ofFloat(groupedPhotosListView,View.ALPHA,show ? 1.0f : 0.0f));
    arrayList.add(ObjectAnimator.ofFloat(bottomLayout,View.ALPHA,show ? 1.0f : 0.0f));
    if (captionTextView.getTag() != null) {
      arrayList.add(ObjectAnimator.ofFloat(captionTextView,View.ALPHA,show ? 1.0f : 0.0f));
    }
    currentActionBarAnimation=new AnimatorSet();
    currentActionBarAnimation.playTogether(arrayList);
    if (!show) {
      currentActionBarAnimation.addListener(new AnimatorListenerAdapter(){
        @Override public void onAnimationEnd(        Animator animation){
          if (currentActionBarAnimation != null && currentActionBarAnimation.equals(animation)) {
            actionBar.setVisibility(View.GONE);
            if (videoPlayer != null) {
              bottomLayout.setVisibility(View.GONE);
            }
            if (captionTextView.getTag() != null) {
              captionTextView.setVisibility(View.GONE);
            }
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
    bottomLayout.setAlpha(show ? 1.0f : 0.0f);
    if (captionTextView.getTag() != null) {
      captionTextView.setAlpha(show ? 1.0f : 0.0f);
    }
    if (!show) {
      actionBar.setVisibility(View.GONE);
      if (videoPlayer != null) {
        bottomLayout.setVisibility(View.GONE);
      }
      if (captionTextView.getTag() != null) {
        captionTextView.setVisibility(View.GONE);
      }
    }
  }
}
