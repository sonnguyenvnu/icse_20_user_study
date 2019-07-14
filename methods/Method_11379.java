/** 
 * Tells the column that the next character it should show is  {@param targetChar}. This can change can either be animated or instant depending on the animation progress set by {@link #setAnimationProgress(float)}.
 */
void setTargetChar(char targetChar){
  this.targetChar=targetChar;
  this.sourceWidth=this.currentWidth;
  this.targetWidth=metrics.getCharWidth(targetChar);
  this.minimumRequiredWidth=Math.max(this.sourceWidth,this.targetWidth);
  setCharacterIndices();
  final boolean scrollDown=endIndex >= startIndex;
  directionAdjustment=scrollDown ? 1 : -1;
  previousBottomDelta=currentBottomDelta;
  currentBottomDelta=0f;
}
