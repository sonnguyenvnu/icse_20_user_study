/** 
 * ??Snackbar?????? ?:?????,??margins???? gravityFrameLayout,?margins??. ???margins??,????? gravityFrameLayout,? show() ???? margins
 * @param left
 * @param top
 * @param right
 * @param bottom
 * @return
 */
public SnackbarUtils margins(int left,int top,int right,int bottom){
  if (getSnackbar() != null) {
    ViewGroup.LayoutParams params=getSnackbar().getView().getLayoutParams();
    ((ViewGroup.MarginLayoutParams)params).setMargins(left,top,right,bottom);
    getSnackbar().getView().setLayoutParams(params);
  }
  return this;
}
