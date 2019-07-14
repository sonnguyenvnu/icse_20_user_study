private static ColorStateList createEditTextColorStateList(Context context,int color){
  int[][] states=new int[3][];
  int[] colors=new int[3];
  int i=0;
  states[i]=new int[]{-android.R.attr.state_enabled};
  colors[i]=Utils.getColor(context,R.color.text_disabled);
  i++;
  states[i]=new int[]{-android.R.attr.state_pressed,-android.R.attr.state_focused};
  colors[i]=Utils.getColor(context,R.color.grey);
  i++;
  states[i]=new int[]{};
  colors[i]=color;
  return new ColorStateList(states,colors);
}
