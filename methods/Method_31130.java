/** 
 * Overrides the configuration from the config file with the properties passed in directly from the command-line.
 * @param config The properties to override.
 * @param args   The command-line arguments that were passed in.
 */
static void overrideConfigurationWithArgs(Map<String,String> config,String[] args){
  for (  String arg : args) {
    if (isPropertyArgument(arg)) {
      config.put(getArgumentProperty(arg),getArgumentValue(arg));
    }
  }
}
