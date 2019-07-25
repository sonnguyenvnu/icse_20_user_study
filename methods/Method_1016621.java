/** 
 * Creates and configures a new  {@link SpotlessEclipseFramework} if there is none.If there is already a an instance, the call is ignored.
 * @param core Activators of core bundles
 * @param config Framework service configuration
 * @param plugins Eclipse plugins to start
 * @return False if the {@link SpotlessEclipseFramework} instance already exists, true otherwise.
 * @throws BundleException Throws exception in case the setup failed.
 */
public synchronized static boolean setup(Consumer<SpotlessEclipseCoreConfig> core,Consumer<SpotlessEclipseServiceConfig> config,Consumer<SpotlessEclipsePluginConfig> plugins) throws BundleException {
  if (null != INSTANCE) {
    return false;
  }
  SpotlessEclipseCoreConfig coreConfig=new SpotlessEclipseCoreConfig();
  core.accept(coreConfig);
  INSTANCE=new SpotlessEclipseFramework(coreConfig);
  config.accept(INSTANCE.getServiceConfig());
  SpotlessEclipsePluginConfig pluginConfig=new SpotlessEclipsePluginConfig();
  plugins.accept(pluginConfig);
  for (  BundleConfig.Entry plugin : pluginConfig.get()) {
    INSTANCE.addPlugin(plugin.state,plugin.activator);
  }
  return true;
}
