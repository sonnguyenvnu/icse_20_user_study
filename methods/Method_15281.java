/** 
 * ????
 * @param cornerRadiusSize
 * @return
 */
private static DisplayImageOptions getOption(int cornerRadiusSize){
  return getOption(cornerRadiusSize,cornerRadiusSize <= 0 ? R.drawable.square_alpha : R.drawable.oval_alpha);
}
