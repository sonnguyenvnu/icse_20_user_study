/** 
 * ??Snackbar?????View??? ?:????????Snackbar,?? {@link SnackbarUtils#calculateSnackBarHeight()}???????Snackbar?????
 * @param targetView     ??View
 * @param contentViewTop Activity??View???? ?????????
 * @param marginLeft     ???
 * @param marginRight    ???
 * @return
 */
public SnackbarUtils above(View targetView,int contentViewTop,int marginLeft,int marginRight){
  if (getSnackbar() != null) {
    marginLeft=marginLeft <= 0 ? 0 : marginLeft;
    marginRight=marginRight <= 0 ? 0 : marginRight;
    int[] locations=new int[2];
    targetView.getLocationOnScreen(locations);
    UILog.dTag(TAG,"??????:" + locations[0] + "==??????:" + locations[1]);
    int snackbarHeight=calculateSnackBarHeight();
    UILog.dTag(TAG,"Snackbar??:" + snackbarHeight);
    if (locations[1] >= contentViewTop + snackbarHeight) {
      gravityFrameLayout(Gravity.BOTTOM);
      ViewGroup.LayoutParams params=getSnackbar().getView().getLayoutParams();
      ((ViewGroup.MarginLayoutParams)params).setMargins(marginLeft,0,marginRight,getSnackbar().getView().getResources().getDisplayMetrics().heightPixels - locations[1]);
      getSnackbar().getView().setLayoutParams(params);
    }
  }
  return this;
}
