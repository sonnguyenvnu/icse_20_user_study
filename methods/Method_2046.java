/** 
 * Utility method which set the bgColor based on configuration values
 * @param view The View to change the bgColor to
 * @param config The Config object
 */
public static void setBgColor(View view,final Config config){
  int[] colors=view.getContext().getResources().getIntArray(R.array.bg_colors);
  final int bgColor=colors[config.bgColor];
  view.setBackgroundColor(bgColor);
}
