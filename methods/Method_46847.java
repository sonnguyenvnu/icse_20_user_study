/** 
 * called as to toggle selection of any item in adapter
 * @param position  the position of the item
 * @param imageView the circular {@link CircleGradientDrawable} that is to be animated
 */
private void toggleChecked(int position,ImageView imageView){
  compressedExplorerFragment.stopAnim();
  stoppedAnimation=true;
  Animation animation;
  if (itemsChecked[position]) {
    animation=AnimationUtils.loadAnimation(context,R.anim.check_out);
  }
 else {
    animation=AnimationUtils.loadAnimation(context,R.anim.check_in);
  }
  if (imageView != null) {
    imageView.setAnimation(animation);
  }
 else {
  }
  itemsChecked[position]=!itemsChecked[position];
  notifyDataSetChanged();
  if (!compressedExplorerFragment.selection || compressedExplorerFragment.mActionMode == null) {
    compressedExplorerFragment.selection=true;
    compressedExplorerFragment.mActionMode=compressedExplorerFragment.mainActivity.getAppbar().getToolbar().startActionMode(compressedExplorerFragment.mActionModeCallback);
  }
  compressedExplorerFragment.mActionMode.invalidate();
  if (getCheckedItemPositions().size() == 0) {
    compressedExplorerFragment.selection=false;
    compressedExplorerFragment.mActionMode.finish();
    compressedExplorerFragment.mActionMode=null;
  }
}
