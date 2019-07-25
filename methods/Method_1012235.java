/** 
 * ??Snackbar?????View??? ?:????????Snackbar,?? {@link SnackbarUtils#calculateSnackBarHeight()}???????Snackbar?????
 * @param targetView     ??View
 * @param contentViewTop Activity??View???? ?????????
 * @param marginLeft     ???
 * @param marginRight    ???
 * @return
 */
public SnackbarUtils bellow(View targetView,int contentViewTop,int marginLeft,int marginRight){
  if (getSnackbar() != null) {
    marginLeft=marginLeft <= 0 ? 0 : marginLeft;
    marginRight=marginRight <= 0 ? 0 : marginRight;
    int[] locations=new int[2];
    targetView.getLocationOnScreen(locations);
    int snackbarHeight=calculateSnackBarHeight();
    int screenHeight=Utils.getScreenHeight(getSnackbar().getView().getContext());
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
      if (locations[1] + targetView.getHeight() >= contentViewTop && locations[1] + targetView.getHeight() + snackbarHeight + 2 <= screenHeight) {
        gravityFrameLayout(Gravity.BOTTOM);
        ViewGroup.LayoutParams params=getSnackbar().getView().getLayoutParams();
        ((ViewGroup.MarginLayoutParams)params).setMargins(marginLeft,0,marginRight,screenHeight - (locations[1] + targetView.getHeight() + snackbarHeight + 2));
        getSnackbar().getView().setLayoutParams(params);
      }
    }
 else {
      if (locations[1] + targetView.getHeight() >= contentViewTop && locations[1] + targetView.getHeight() + snackbarHeight <= screenHeight) {
        gravityFrameLayout(Gravity.BOTTOM);
        ViewGroup.LayoutParams params=getSnackbar().getView().getLayoutParams();
        ((ViewGroup.MarginLayoutParams)params).setMargins(marginLeft,0,marginRight,screenHeight - (locations[1] + targetView.getHeight() + snackbarHeight));
        getSnackbar().getView().setLayoutParams(params);
      }
    }
  }
  return this;
}
