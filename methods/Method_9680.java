private boolean onItemLongClick(WallpaperCell view,Object object,int index){
  if (actionBar.isActionModeShowed() || getParentActivity() == null || !(object instanceof TLRPC.TL_wallPaper)) {
    return false;
  }
  TLRPC.TL_wallPaper wallPaper=(TLRPC.TL_wallPaper)object;
  AndroidUtilities.hideKeyboard(getParentActivity().getCurrentFocus());
  selectedWallPapers.put(wallPaper.id,wallPaper);
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
  actionBar.showActionMode();
  view.setChecked(index,true,true);
  return true;
}
