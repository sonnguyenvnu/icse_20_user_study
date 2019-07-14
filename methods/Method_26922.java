/** 
 * Returns the child Transition at the specified position in the TransitionSet.
 * @param index The position of the Transition to retrieve.
 * @see #addTransition(Transition)
 * @see #getTransitionCount()
 */
@Nullable public Transition getTransitionAt(int index){
  if (index < 0 || index >= mTransitions.size()) {
    return null;
  }
  return mTransitions.get(index);
}
