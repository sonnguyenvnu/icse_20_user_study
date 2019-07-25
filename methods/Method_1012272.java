/** 
 * ??View????
 */
public void show(){
  if (isDialog()) {
    showDialog();
  }
 else {
    if (isShowing()) {
      return;
    }
    isShowing=true;
    onAttached(rootView);
    rootView.requestFocus();
  }
}
