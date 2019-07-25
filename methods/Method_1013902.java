/** 
 * This method unregister a  {@link Rule} and it stops working. It is called when the {@link Rule} isremoved, updated or disabled. Also it is called when some  {@link ModuleHandlerFactory} is disposed or some{@link ModuleType} is updated.
 * @param r rule that should be unregistered.
 */
private void unregister(WrappedRule r){
  String rUID=r.getUID();
synchronized (this) {
    TriggerHandlerCallbackImpl callback=thCallbacks.remove(rUID);
    if (callback != null) {
      callback.dispose();
    }
  }
  removeModuleHandlers(r.getModules(),rUID);
}
