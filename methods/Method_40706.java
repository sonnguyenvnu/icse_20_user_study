/** 
 * Returns the global scope (i.e. the module scope for the current module).
 */
@NotNull public State getGlobalTable(){
  State result=getStateOfType(StateType.GLOBAL);
  if (result != null) {
    return result;
  }
 else {
    _.die("Couldn't find global table. Shouldn't happen");
    return this;
  }
}
