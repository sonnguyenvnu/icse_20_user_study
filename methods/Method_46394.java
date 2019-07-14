/** 
 * ????
 * @param environment      Spring Env
 * @param serverProperties serverProperties
 * @return ??
 */
public static String modId(ConfigurableEnvironment environment,ServerProperties serverProperties){
  String applicationName=environment.getProperty("spring.application.name");
  applicationName=StringUtils.hasText(applicationName) ? applicationName : "application";
  return applicationName + ":" + serverPort(serverProperties);
}
