/** 
 * Called before a transition occurs. This can be used to reorder views, set their transition names, etc. The transition will begin when  {@code onTransitionPreparedListener} is called.
 * @param container  The container these Views are hosted in
 * @param from       The previous View in the container or {@code null} if there was no Controller before this transition
 * @param to         The next View that should be put in the container or {@code null} if no Controller is being transitioned to
 * @param transition The transition that is being prepared for
 * @param isPush     True if this is a push transaction, false if it's a pop
 */
public void prepareForTransition(@NonNull ViewGroup container,@Nullable View from,@Nullable View to,@NonNull Transition transition,boolean isPush,@NonNull OnTransitionPreparedListener onTransitionPreparedListener){
  onTransitionPreparedListener.onPrepared();
}
