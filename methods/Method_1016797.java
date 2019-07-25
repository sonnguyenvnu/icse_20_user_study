/** 
 * Resets the previous gradients and values that are used to approximate the Hessian. NOTE - If the  {@link Optimizable} objectis modified externally, this method should be called to avoid IllegalStateExceptions. 
 */
public void reset(){
  g=null;
}
