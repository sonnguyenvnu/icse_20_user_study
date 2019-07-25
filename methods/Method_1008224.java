/** 
 * Initializes SecurityManager for the environment Can only happen once!
 * @param environment configuration for generating dynamic permissions
 * @param filterBadDefaults true if we should filter out bad java defaults in the system policy.
 */
static void configure(Environment environment,boolean filterBadDefaults) throws IOException, NoSuchAlgorithmException {
  Map<String,URL> codebases=getCodebaseJarMap(JarHell.parseClassPath());
  Policy.setPolicy(new ESPolicy(codebases,createPermissions(environment),getPluginPermissions(environment),filterBadDefaults));
  final String[] classesThatCanExit=new String[]{ElasticsearchUncaughtExceptionHandler.PrivilegedHaltAction.class.getName().replace("$","\\$"),Command.class.getName()};
  System.setSecurityManager(new SecureSM(classesThatCanExit));
  selfTest();
}
