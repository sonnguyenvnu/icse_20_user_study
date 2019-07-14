/** 
 * Initializes this controller with the new id and caller context. This allows for reusing of the existing controller instead of instantiating a new one. This method should be called when the controller is in detached state.
 * @param id unique id for this controller
 * @param callerContext tag and context for this controller
 */
protected void initialize(String id,Object callerContext){
  init(id,callerContext);
  mJustConstructed=false;
}
