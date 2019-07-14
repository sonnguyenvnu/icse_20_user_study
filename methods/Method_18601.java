/** 
 * Creates a  {@link RenderThreadTransition} with the given duration and {@link android.animation.TimeInterpolator} that runs on the Render Thread.
 */
public static TransitionAnimator renderThread(int durationMs,TimeInterpolator interpolator){
  return new RenderThreadAnimator(0,durationMs,interpolator);
}
