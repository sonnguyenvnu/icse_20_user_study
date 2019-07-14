/** 
 * Creates a sequence of  {@link Transition}s that will run one after another.
 */
public static <T extends Transition>TransitionSet sequence(T... transitions){
  return new SequenceTransitionSet(transitions);
}
