/** 
 * Plugin initialization is after Spring has finished its startup and freezeConfiguration is called. This will override freeze method to init plugin - plugin will be initialized and the configuration remains unfrozen, so bean (re)definition may be done by the plugin.
 */
@OnClassLoadEvent(classNameRegexp="org.springframework.beans.factory.support.DefaultListableBeanFactory") public static void register(CtClass clazz) throws NotFoundException, CannotCompileException {
  StringBuilder src=new StringBuilder("{");
  src.append("setCacheBeanMetadata(false);");
  src.append(PluginManagerInvoker.buildInitializePlugin(SpringPlugin.class));
  src.append(PluginManagerInvoker.buildCallPluginMethod(SpringPlugin.class,"init","org.springframework.core.SpringVersion.getVersion()",String.class.getName()));
  src.append("}");
  for (  CtConstructor constructor : clazz.getDeclaredConstructors()) {
    constructor.insertBeforeBody(src.toString());
  }
  CtMethod method=clazz.getDeclaredMethod("freezeConfiguration");
  method.insertBefore("org.hotswap.agent.plugin.spring.ResetSpringStaticCaches.resetBeanNamesByType(this); " + "setAllowRawInjectionDespiteWrapping(true); ");
}
