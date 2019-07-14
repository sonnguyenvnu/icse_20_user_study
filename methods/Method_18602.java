/** 
 * Creates a  {@link RenderThreadTransition} with the given duration, delay and {@link android.animation.TimeInterpolator} that runs on the Render Thread. Warning: the delay will beconsidered as a part of the animation, you may consider using  {@link Transition#delay(int,Transition)} ()} instead, but this way the delay will be handled on the UI thread)
 */
public static TransitionAnimator renderThread(int delayMs,int durationMs,Interpolator interpolator){
  return new RenderThreadAnimator(delayMs,durationMs,interpolator);
}
