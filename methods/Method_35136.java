/** 
 * Overrides the  {@link ControllerChangeHandler} that should be used for pushing this Controller. If this is anon-null value, it will be used instead of the handler from  the  {@link RouterTransaction}.
 */
public void overridePushHandler(@Nullable ControllerChangeHandler overriddenPushHandler){
  this.overriddenPushHandler=overriddenPushHandler;
}
