/** 
 * ??????????Header??
 */
private void scrollChangeHeader(int scrolledY){
  DebugUtil.error("---scrolledY:  " + scrolledY);
  DebugUtil.error("-----slidingDistance: " + slidingDistance);
  if (scrolledY < 0) {
    scrolledY=0;
  }
  float alpha=Math.abs(scrolledY) * 1.0f / (slidingDistance);
  DebugUtil.error("----alpha:  " + alpha);
  Drawable drawable=binding.ivTitleHeadBg.getDrawable();
  if (scrolledY <= slidingDistance) {
    drawable.mutate().setAlpha((int)(alpha * 255));
    binding.ivTitleHeadBg.setImageDrawable(drawable);
  }
 else {
    drawable.mutate().setAlpha(255);
    binding.ivTitleHeadBg.setImageDrawable(drawable);
  }
}
