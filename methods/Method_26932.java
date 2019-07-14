/** 
 * Creates an animator that can be used for x and/or y translations. When interrupted, it sets a tag to keep track of the position so that it may be continued from position.
 * @param view The view being moved. This may be in the overlay for onDisappear.
 * @param values The values containing the view in the view hierarchy.
 * @param viewPosX The x screen coordinate of view
 * @param viewPosY The y screen coordinate of view
 * @param startX The start translation x of view
 * @param startY The start translation y of view
 * @param endX The end translation x of view
 * @param endY The end translation y of view
 * @param interpolator The interpolator to use with this animator.
 * @return An animator that moves from (startX, startY) to (endX, endY) unless there wasa previous interruption, in which case it moves from the current position to (endX, endY).
 */
@Nullable public static Animator createAnimation(View view,TransitionValues values,int viewPosX,int viewPosY,float startX,float startY,float endX,float endY,TimeInterpolator interpolator,@NonNull Transition transition){
  if (TRANSLATIONS_PROPERTY == null) {
    return null;
  }
  float terminalX=view.getTranslationX();
  float terminalY=view.getTranslationY();
  int[] startPosition=(int[])values.view.getTag(R.id.transitionPosition);
  if (startPosition != null) {
    startX=startPosition[0] - viewPosX + terminalX;
    startY=startPosition[1] - viewPosY + terminalY;
  }
  int startPosX=viewPosX + Math.round(startX - terminalX);
  int startPosY=viewPosY + Math.round(startY - terminalY);
  view.setTranslationX(startX);
  view.setTranslationY(startY);
  Animator anim=AnimatorUtils.ofPointF(view,TRANSLATIONS_PROPERTY,startX,startY,endX,endY);
  if (anim != null) {
    TransitionPositionListener listener=new TransitionPositionListener(view,values.view,startPosX,startPosY,terminalX,terminalY);
    transition.addListener(listener);
    anim.addListener(listener);
    AnimatorUtils.addPauseListener(anim,listener);
    anim.setInterpolator(interpolator);
  }
  return anim;
}
