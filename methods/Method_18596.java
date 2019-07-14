/** 
 * Creates a set of  {@link Transition}s that will run in parallel but starting on a stagger.
 */
public static <T extends Transition>TransitionSet stagger(int staggerMs,T... transitions){
  return new ParallelTransitionSet(staggerMs,transitions);
}
