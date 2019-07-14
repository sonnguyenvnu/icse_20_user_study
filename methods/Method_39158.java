/** 
 * Registers actions and applies proxetta on actions that are not already registered. We need to define  {@link ActionDefinition} before we apply the proxy, usingtarget action class.
 */
@Override public synchronized ActionRuntime registerAction(Class actionClass,final Method actionMethod,ActionDefinition actionDefinition){
  if (proxettaSupplier == null) {
    return super.registerAction(actionClass,actionMethod,actionDefinition);
  }
  if (actionDefinition == null) {
    actionDefinition=actionMethodParser.parseActionDefinition(actionClass,actionMethod);
  }
  Class existing=proxyActionClasses.get(actionClass);
  if (existing == null) {
    final Proxetta proxetta=proxettaSupplier.get();
    existing=proxetta.proxy().setTarget(actionClass).define();
    proxyActionClasses.put(actionClass,existing);
  }
  actionClass=existing;
  return super.registerAction(actionClass,actionMethod,actionDefinition);
}
