/** 
 * This method unregister a  {@link Rule} and it stops working. It is called when some{@link ModuleHandlerFactory} is disposed or some {@link ModuleType} is updated. The {@link Rule} isavailable but its state should become  {@link RuleStatus#UNINITIALIZED}.
 * @param r rule that should be unregistered.
 * @param detail provides the {@link RuleStatusDetail}, corresponding to the new <b>uninitialized</b> status, should be  {@code null} if the status will be skipped.
 * @param msg provides the {@link RuleStatusInfo} description, corresponding to the new <b>uninitialized</b>status, should be  {@code null} if the status will be skipped.
 */
private void unregister(@Nullable WrappedRule r,@Nullable RuleStatusDetail detail,@Nullable String msg){
  if (r != null) {
    unregister(r);
    setStatus(r.getUID(),new RuleStatusInfo(RuleStatus.UNINITIALIZED,detail,msg));
  }
}
