/** 
 * Creates a set of  {@link Transition}s that will run in parallel. 
 */
@ThreadSafe(enableChecks=false) public static <T extends Transition>TransitionSet parallel(T... transitions){
  return new ParallelTransitionSet(transitions);
}
