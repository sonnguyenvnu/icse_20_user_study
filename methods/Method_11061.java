public int dip2px(float dipValue){
  final float scale=getContext().getResources().getDisplayMetrics().density;
  return (int)(dipValue * scale + 0.5f);
}
