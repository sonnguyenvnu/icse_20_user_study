/** 
 * Starts the Joy. Returns the  {@link JoddJoyRuntime runtime}, set of running Joy components.
 */
public JoddJoyRuntime start(final ServletContext servletContext){
  LoggerProvider loggerProvider=null;
  if (loggerProviderSupplier != null) {
    loggerProvider=loggerProviderSupplier.get();
  }
  if (loggerProvider == null) {
    loggerProvider=SimpleLogger.PROVIDER;
  }
  LoggerFactory.setLoggerProvider(loggerProvider);
  log=LoggerFactory.getLogger(JoddJoy.class);
  printLogo();
  log.info("Ah, Joy!");
  log.info("Logging using: " + loggerProvider.getClass().getSimpleName());
  joyPropsConsumers.accept(joyProps);
  joyProxettaConsumers.accept(joyProxetta);
  joyDbConsumers.accept(joyDb);
  joyPetiteConsumers.accept(joyPetite);
  try {
    joyPaths.start();
    joyProps.start();
    joyProxetta.start();
    joyScanner.start();
    joyPetite.start();
    joyPetite.getPetiteContainer().addBean(appName + ".core",this);
    joyPetite.getPetiteContainer().addBean(appName + ".scanner",joyScanner);
    joyDb.start();
    joyMadvoc.setServletContext(servletContext);
    joyMadvoc.start();
    runJoyInitBeans();
    joyScanner.stop();
  }
 catch (  Exception ex) {
    if (log != null) {
      log.error(ex.toString(),ex);
    }
 else {
      System.out.println(ex.toString());
      ex.printStackTrace();
    }
    stop();
    throw ex;
  }
  joyPetite.printBeans(100);
  joyDb.printEntities(100);
  joyMadvoc.printRoutes(100);
  System.out.println(Chalk256.chalk().yellow().on("Joy") + " is up. Enjoy!");
  log.info("Joy is up. Enjoy!");
  if (joyDb.isDatabaseEnabled()) {
    return new JoddJoyRuntime(appName,joyPaths.getAppDir(),joyProps.getProps(),joyProxetta.getProxetta(),joyPetite.getPetiteContainer(),joyMadvoc.getWebApp(),joyDb.isDatabaseEnabled(),joyDb.getConnectionProvider(),joyDb.getJtxManager());
  }
 else {
    return new JoddJoyRuntime(appName,joyPaths.getAppDir(),joyProps.getProps(),joyProxetta.getProxetta(),joyPetite.getPetiteContainer(),joyMadvoc.getWebApp());
  }
}
