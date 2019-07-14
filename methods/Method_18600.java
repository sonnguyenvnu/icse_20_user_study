/** 
 * Creates a  {@link RenderThreadTransition} that runs on the Render Thread. 
 */
public static TransitionAnimator renderThread(int delayMs,int durationMs){
  return new RenderThreadAnimator(delayMs,durationMs,DEFAULT_INTERPOLATOR);
}
