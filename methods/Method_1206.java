/** 
 * Applies the given rounding params on the specified rounded drawable.
 */
static void applyRoundingParams(Rounded rounded,RoundingParams roundingParams){
  rounded.setCircle(roundingParams.getRoundAsCircle());
  rounded.setRadii(roundingParams.getCornersRadii());
  rounded.setBorder(roundingParams.getBorderColor(),roundingParams.getBorderWidth());
  rounded.setPadding(roundingParams.getPadding());
  rounded.setScaleDownInsideBorders(roundingParams.getScaleDownInsideBorders());
  rounded.setPaintFilterBitmap(roundingParams.getPaintFilterBitmap());
}
