/** 
 * Starts the application with the specified bean class and bean modules.
 * @param classes the specified bean class, nullable
 */
public static void start(final Collection<Class<?>> classes){
  LOGGER.log(Level.DEBUG,"Initializing Latke IoC container");
  final Configurator configurator=getInstance().getConfigurator();
  if (null != classes && !classes.isEmpty()) {
    configurator.createBeans(classes);
  }
  LOGGER.log(Level.DEBUG,"Initialized Latke IoC container");
}
