/** 
 * @return a RuntimeValue that resolves to an offset relative to the current value of some mountcontent property.
 */
public static DimensionValue offsetDip(Context context,int valueDp){
  final DisplayMetrics displayMetrics=context.getResources().getDisplayMetrics();
  final float valuePx=displayMetrics.density * valueDp;
  return offsetPx(valuePx);
}
