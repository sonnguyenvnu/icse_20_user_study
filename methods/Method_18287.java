/** 
 * Returns the state handler instance currently held by LayoutState and nulls it afterwards.
 * @return the state handler
 */
@CheckReturnValue StateHandler consumeStateHandler(){
  final StateHandler stateHandler=mStateHandler;
  mStateHandler=null;
  return stateHandler;
}
