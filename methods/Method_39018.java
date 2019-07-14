/** 
 * Lookup for the action configuration. Typically, the input argument is either the action type or annotation type.
 */
public ActionConfig lookup(final Class actionTypeOrAnnotationType){
  final ActionConfig actionConfig=actionConfigs.get(actionTypeOrAnnotationType);
  if (actionConfig == null) {
    throw new MadvocException("ActionConfiguration not registered:" + actionTypeOrAnnotationType.getName());
  }
  return actionConfig;
}
