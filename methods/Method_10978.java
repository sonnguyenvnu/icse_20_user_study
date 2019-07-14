private int dp2px(int value){
  final float scale=getContext().getResources().getDisplayMetrics().density;
  return (int)(value * scale + 0.5f);
}
