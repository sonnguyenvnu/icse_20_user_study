/** 
 * Configures Flyway using FLYWAY_* environment variables.
 */
public void configureUsingEnvVars(){
  configure(ConfigUtils.environmentVariablesToPropertyMap());
}
