/** 
 * Overrides the  {@link ControllerChangeHandler} that should be used for popping this Controller. If this is anon-null value, it will be used instead of the handler from  the  {@link RouterTransaction}.
 */
public void overridePopHandler(@Nullable ControllerChangeHandler overriddenPopHandler){
  this.overriddenPopHandler=overriddenPopHandler;
}
