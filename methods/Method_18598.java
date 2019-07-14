/** 
 * Creates a  {@link SpringTransition} with the given tension and friction. Spring physicsimplementation is taken from Rebound library and we recommend to use demo provided at <a href="http://facebook.github.io/rebound/">http://facebook.github.io/rebound</a> to have a better sense of how friction and tension values work together.
 */
public static TransitionAnimator springWithConfig(final double tension,final double friction){
  return new SpringTransitionAnimator(tension,friction);
}
