/** 
 * Happy hacking :) This is really needed only if you cannot modify framework sources to be more "reload friendly" and you need to hook into framework execution.
 * @param clazz javaassist class file, which you can modify before it's loaded.See @{link OnClassLoadEvent} javadoc to see available parameters.
 * @throws CannotCompileException error in new method definition
 */
@OnClassLoadEvent(classNameRegexp="org.hotswap.agent.example.framework.PrinterSourceScanner") public void register(CtClass clazz) throws CannotCompileException {
  String callbackMethod=PluginManagerInvoker.buildCallPluginMethod(PrinterHAPlugin.class,"setAutoDiscoveredPrintSources","$_","java.util.List");
  CtMethod scanPrintSourcesMethod=Arrays.stream(clazz.getDeclaredMethods()).filter(m -> m.getName().equals("scanPrintSources")).findFirst().orElseThrow(IllegalStateException::new);
  scanPrintSourcesMethod.insertAfter(callbackMethod);
}
