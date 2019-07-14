/** 
 * ??????
 * @param position ??tab??
 */
public void showDot(int position){
  if (position >= mTabCount) {
    position=mTabCount - 1;
  }
  showMsg(position,0);
}
