/** 
 * called as to toggle selection of any item in adapter
 * @param position  the position of the item
 * @param imageView the check {@link CircleGradientDrawable} that is to be animated
 */
public void toggleChecked(int position,ImageView imageView){
  if (itemsDigested.get(position).getChecked() == ListItem.UNCHECKABLE) {
    throw new IllegalArgumentException("You have checked a header");
  }
  if (!stoppedAnimation)   mainFrag.stopAnimation();
  if (itemsDigested.get(position).getChecked() == ListItem.CHECKED) {
    itemsDigested.get(position).setChecked(false);
    Animation iconAnimation=AnimationUtils.loadAnimation(context,R.anim.check_out);
    if (imageView != null) {
      imageView.startAnimation(iconAnimation);
    }
 else {
    }
  }
 else {
    itemsDigested.get(position).setChecked(true);
    Animation iconAnimation=AnimationUtils.loadAnimation(context,R.anim.check_in);
    if (imageView != null) {
      imageView.startAnimation(iconAnimation);
    }
 else {
    }
    if (mainFrag.mActionMode == null || !mainFrag.selection) {
      mainFrag.selection=true;
      mainFrag.mActionMode=mainFrag.getMainActivity().startSupportActionMode(mainFrag.mActionModeCallback);
    }
  }
  notifyItemChanged(position);
  if (mainFrag.mActionMode != null && mainFrag.selection) {
    mainFrag.mActionMode.invalidate();
  }
  if (getCheckedItems().size() == 0) {
    mainFrag.selection=false;
    mainFrag.mActionMode.finish();
    mainFrag.mActionMode=null;
  }
}
