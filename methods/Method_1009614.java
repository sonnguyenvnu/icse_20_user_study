public static void setup(final ShutdownHandler shutdownHandler,String appName) throws Exception {
  System.setProperty("apple.laf.useScreenMenuBar","true");
  System.setProperty("com.apple.mrj.application.apple.menu.about.name",appName);
  System.setProperty("apple.awt.showGrowBox","true");
  Class appClass=Class.forName("com.apple.eawt.Application");
  Object application=appClass.newInstance();
  Class listenerClass=Class.forName("com.apple.eawt.ApplicationListener");
  Method addAppListmethod=appClass.getDeclaredMethod("addApplicationListener",listenerClass);
  Class adapterClass=Class.forName("com.apple.eawt.ApplicationAdapter");
  Object listener=AppListenerProxy.newInstance(adapterClass.newInstance(),shutdownHandler);
  addAppListmethod.invoke(application,listener);
}
