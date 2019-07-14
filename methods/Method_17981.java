/** 
 * Provides a new instance from the StateHandler pool that is initialized with the information from the StateHandler currently held by the ComponentTree. Once the state updates have been applied and we are back in the main thread the state handler gets released to the pool.
 * @return a copy of the state handler instance held by ComponentTree.
 */
public synchronized StateHandler acquireStateHandler(){
  return StateHandler.createNewInstance(mStateHandler);
}
