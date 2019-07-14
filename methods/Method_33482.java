/** 
 * ??????????Header??
 */
private void scrollChangeHeader(int scrolledY){
  if (scrolledY < 0) {
    scrolledY=0;
  }
  float alpha=Math.abs(scrolledY) * 1.0f / (slidingDistance);
  Drawable drawable=bindingTitleView.ivBaseTitlebarBg.getDrawable();
  if (drawable == null) {
    return;
  }
  if (scrolledY <= slidingDistance) {
    drawable.mutate().setAlpha((int)(alpha * 255));
    bindingTitleView.ivBaseTitlebarBg.setImageDrawable(drawable);
  }
 else {
    drawable.mutate().setAlpha(255);
    bindingTitleView.ivBaseTitlebarBg.setImageDrawable(drawable);
  }
}
