/** 
 * @return the height value that LithoView should be animating from. If this returns non-negativevalue, we will override the measured height with this value so that initial animated value is correctly applied.
 */
@ThreadConfined(ThreadConfined.UI) int getInitialAnimatedLithoViewHeight(int currentAnimatedHeight,boolean hasNewComponentTree){
  return getInitialAnimatedLithoViewDimension(currentAnimatedHeight,hasNewComponentTree,mRootHeightAnimation,AnimatedProperties.HEIGHT);
}
