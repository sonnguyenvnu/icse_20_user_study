/** 
 * Creates a  {@link RenderThreadTransition} that runs on the Render Thread. 
 */
public static TransitionAnimator renderThread(){
  return new RenderThreadAnimator(0,DEFAULT_DURATION,DEFAULT_INTERPOLATOR);
}
