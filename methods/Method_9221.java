private void updateSelectedCount(){
  if (commentView == null) {
    return;
  }
  if (!dialogsAdapter.hasSelectedDialogs()) {
    if (dialogsType == 3 && selectAlertString == null) {
      actionBar.setTitle(LocaleController.getString("ForwardTo",R.string.ForwardTo));
    }
 else {
      actionBar.setTitle(LocaleController.getString("SelectChat",R.string.SelectChat));
    }
    if (commentView.getTag() != null) {
      commentView.hidePopup(false);
      commentView.closeKeyboard();
      AnimatorSet animatorSet=new AnimatorSet();
      animatorSet.playTogether(ObjectAnimator.ofFloat(commentView,View.TRANSLATION_Y,0,commentView.getMeasuredHeight()));
      animatorSet.setDuration(180);
      animatorSet.setInterpolator(new DecelerateInterpolator());
      animatorSet.addListener(new AnimatorListenerAdapter(){
        @Override public void onAnimationEnd(        Animator animation){
          commentView.setVisibility(View.GONE);
        }
      }
);
      animatorSet.start();
      commentView.setTag(null);
      listView.requestLayout();
    }
  }
 else {
    if (commentView.getTag() == null) {
      commentView.setFieldText("");
      commentView.setVisibility(View.VISIBLE);
      AnimatorSet animatorSet=new AnimatorSet();
      animatorSet.playTogether(ObjectAnimator.ofFloat(commentView,View.TRANSLATION_Y,commentView.getMeasuredHeight(),0));
      animatorSet.setDuration(180);
      animatorSet.setInterpolator(new DecelerateInterpolator());
      animatorSet.addListener(new AnimatorListenerAdapter(){
        @Override public void onAnimationEnd(        Animator animation){
          commentView.setTag(2);
          commentView.requestLayout();
        }
      }
);
      animatorSet.start();
      commentView.setTag(1);
    }
    actionBar.setTitle(LocaleController.formatPluralString("Recipient",dialogsAdapter.getSelectedDialogs().size()));
  }
}
