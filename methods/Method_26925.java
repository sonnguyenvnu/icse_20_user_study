/** 
 * Sets up listeners for each of the child transitions. This is used to determine when this transition set is finished (all child transitions must finish first).
 */
private void setupStartEndListeners(){
  TransitionSetListener listener=new TransitionSetListener(this);
  for (  Transition childTransition : mTransitions) {
    childTransition.addListener(listener);
  }
  mCurrentListeners=mTransitions.size();
}
