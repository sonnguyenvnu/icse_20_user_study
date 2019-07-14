public static ColorStateList textSelector(int normalColor,int pressedColor){
  return new ColorStateList(new int[][]{new int[]{android.R.attr.state_pressed},new int[]{android.R.attr.state_focused},new int[]{android.R.attr.state_activated},new int[]{android.R.attr.state_selected},new int[]{}},new int[]{pressedColor,pressedColor,pressedColor,pressedColor,normalColor});
}
