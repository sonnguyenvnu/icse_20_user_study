/** 
 * Show the dropdown menu
 */
public void expand(){
  if (canShowPopup()) {
    if (!hideArrow) {
      animateArrow(true);
    }
    nothingSelected=true;
    popupWindow.showAsDropDown(this);
  }
}
