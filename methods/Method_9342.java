private boolean onItemLongClick(MessageObject item,View view,int a){
  if (actionBar.isActionModeShowed() || getParentActivity() == null) {
    return false;
  }
  AndroidUtilities.hideKeyboard(getParentActivity().getCurrentFocus());
  selectedFiles[item.getDialogId() == dialog_id ? 0 : 1].put(item.getId(),item);
  if (!item.canDeleteMessage(null)) {
    cantDeleteMessagesCount++;
  }
  actionBar.createActionMode().getItem(delete).setVisibility(cantDeleteMessagesCount == 0 ? View.VISIBLE : View.GONE);
  if (gotoItem != null) {
    gotoItem.setVisibility(View.VISIBLE);
  }
  selectedMessagesCountTextView.setNumber(1,false);
  AnimatorSet animatorSet=new AnimatorSet();
  ArrayList<Animator> animators=new ArrayList<>();
  for (int i=0; i < actionModeViews.size(); i++) {
    View view2=actionModeViews.get(i);
    AndroidUtilities.clearDrawableAnimation(view2);
    animators.add(ObjectAnimator.ofFloat(view2,View.SCALE_Y,0.1f,1.0f));
  }
  animatorSet.playTogether(animators);
  animatorSet.setDuration(250);
  animatorSet.start();
  scrolling=false;
  if (view instanceof SharedDocumentCell) {
    ((SharedDocumentCell)view).setChecked(true,true);
  }
 else   if (view instanceof SharedPhotoVideoCell) {
    ((SharedPhotoVideoCell)view).setChecked(a,true,true);
  }
 else   if (view instanceof SharedLinkCell) {
    ((SharedLinkCell)view).setChecked(true,true);
  }
 else   if (view instanceof SharedAudioCell) {
    ((SharedAudioCell)view).setChecked(true,true);
  }
  if (!actionBar.isActionModeShowed()) {
    actionBar.showActionMode(null,actionModeBackground,null,null,null,0);
    resetScroll();
  }
  return true;
}
