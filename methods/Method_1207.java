/** 
 * Resets the rounding params on the specified rounded drawable, so that no rounding occurs.
 */
static void resetRoundingParams(Rounded rounded){
  rounded.setCircle(false);
  rounded.setRadius(0);
  rounded.setBorder(Color.TRANSPARENT,0);
  rounded.setPadding(0);
  rounded.setScaleDownInsideBorders(false);
  rounded.setPaintFilterBitmap(false);
}
