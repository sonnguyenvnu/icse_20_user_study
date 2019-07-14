private static ColorStateList createEditTextColorStateList(int color){
  int[][] states=new int[3][];
  int[] colors=new int[3];
  int i=0;
  states[i]=new int[]{-android.R.attr.state_enabled};
  colors[i]=Color.parseColor("#f6f6f6");
  i++;
  states[i]=new int[]{-android.R.attr.state_pressed,-android.R.attr.state_focused};
  colors[i]=Color.parseColor("#666666");
  i++;
  states[i]=new int[]{};
  colors[i]=color;
  return new ColorStateList(states,colors);
}
