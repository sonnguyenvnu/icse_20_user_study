/** 
 * Registers action configuration for given type.
 */
protected ActionConfig registerNewActionConfiguration(final Class<? extends ActionConfig> actionConfigClass){
  final ActionConfig newActionConfig=createActionConfig(actionConfigClass);
  actionConfigs.put(actionConfigClass,newActionConfig);
  return newActionConfig;
}
