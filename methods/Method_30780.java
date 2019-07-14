/** 
 * Mutates the constant state and returns the new state. Responsible for updating any local copy. <p> This method should never call the super implementation; it should always mutate and return its own constant state.
 * @return the new state
 */
protected DrawableWrapperState mutateConstantState(){
  return mState;
}
