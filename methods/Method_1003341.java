/** 
 * Reset this future to the initial state.
 * @return {@code false} if it was already in initial state
 */
public boolean reset(){
  if (state == S_READY) {
    return false;
  }
  state=S_READY;
  result=null;
  error=null;
  return true;
}
