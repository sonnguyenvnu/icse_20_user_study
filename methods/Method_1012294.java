/** 
 * Show the dropdown menu
 */
public void expand(){
  if (!hasMoreChoice()) {
    if (mOnNoMoreChoiceListener != null) {
      mOnNoMoreChoiceListener.OnNoMoreChoice(this);
    }
    return;
  }
  if (!mHideArrow) {
    animateArrow(true);
  }
  mNothingSelected=true;
  showPopupWindow();
}
