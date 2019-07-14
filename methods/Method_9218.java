private void showOrUpdateActionMode(TLRPC.Dialog dialog,View cell){
  dialogsAdapter.addOrRemoveSelectedDialog(dialog.id,cell);
  ArrayList<Long> selectedDialogs=dialogsAdapter.getSelectedDialogs();
  boolean updateAnimated=false;
  if (actionBar.isActionModeShowed()) {
    if (selectedDialogs.isEmpty()) {
      hideActionMode(true);
      return;
    }
    updateAnimated=true;
  }
 else {
    final ActionBarMenu actionMode=actionBar.createActionMode();
    actionBar.showActionMode();
    if (menuDrawable != null) {
      actionBar.setBackButtonContentDescription(LocaleController.getString("AccDescrGoBack",R.string.AccDescrGoBack));
    }
    if (getPinnedCount() > 1) {
      dialogsAdapter.onReorderStateChanged(true);
      updateVisibleRows(MessagesController.UPDATE_MASK_REORDER);
    }
    AnimatorSet animatorSet=new AnimatorSet();
    ArrayList<Animator> animators=new ArrayList<>();
    for (int a=0; a < actionModeViews.size(); a++) {
      View view=actionModeViews.get(a);
      view.setPivotY(ActionBar.getCurrentActionBarHeight() / 2);
      AndroidUtilities.clearDrawableAnimation(view);
      animators.add(ObjectAnimator.ofFloat(view,View.SCALE_Y,0.1f,1.0f));
    }
    animatorSet.playTogether(animators);
    animatorSet.setDuration(250);
    animatorSet.start();
    if (menuDrawable != null) {
      menuDrawable.setRotateToBack(false);
      menuDrawable.setRotation(1,true);
    }
 else     if (backDrawable != null) {
      backDrawable.setRotation(1,true);
    }
  }
  updateCounters(false);
  selectedDialogsCountTextView.setNumber(selectedDialogs.size(),updateAnimated);
}
