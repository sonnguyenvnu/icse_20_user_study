/** 
 * ??Snackbar??????????????????
 * @param radius
 * @param strokeWidth
 * @param strokeColor
 * @return
 */
public SnackbarUtils radius(int radius,int strokeWidth,@ColorInt int strokeColor){
  if (getSnackbar() != null) {
    GradientDrawable background=getRadiusDrawable(getSnackbar().getView().getBackground());
    if (background != null) {
      radius=radius <= 0 ? 12 : radius;
      strokeWidth=strokeWidth <= 0 ? 1 : (strokeWidth >= getSnackbar().getView().findViewById(R.id.snackbar_text).getPaddingTop() ? 2 : strokeWidth);
      background.setCornerRadius(radius);
      background.setStroke(strokeWidth,strokeColor);
      getSnackbar().getView().setBackgroundDrawable(background);
    }
  }
  return this;
}
