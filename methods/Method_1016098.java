/** 
 * Plugin must be initialized strictly after specified plugin.
 * @param pluginId plugin ID
 * @return "after plugin" initialization strategy
 */
public static InitializationStrategy after(final String pluginId){
  return new InitializationStrategy(StrategyType.after,pluginId);
}
