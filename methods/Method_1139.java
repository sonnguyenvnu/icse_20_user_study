/** 
 * Creates a new instance of RoundedColorDrawable from the given ColorDrawable.
 * @param colorDrawable color drawable to extract the color from
 * @return a new RoundedColorDrawable
 */
@TargetApi(Build.VERSION_CODES.HONEYCOMB) public static RoundedColorDrawable fromColorDrawable(ColorDrawable colorDrawable){
  return new RoundedColorDrawable(colorDrawable.getColor());
}
