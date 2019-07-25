/** 
 * Plugin must be initialized strictly before specified plugin.
 * @param pluginId plugin ID
 * @return "before plugin" initialization strategy
 */
public static InitializationStrategy before(final String pluginId){
  return new InitializationStrategy(StrategyType.before,pluginId);
}
