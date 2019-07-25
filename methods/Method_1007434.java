/** 
 * Basic CdiArchive transformation.
 * @param clazz
 * @param classPool
 * @throws NotFoundException
 * @throws CannotCompileException
 */
@OnClassLoadEvent(classNameRegexp="org.apache.webbeans.config.BeansDeployer") public static void transform(CtClass clazz,ClassPool classPool) throws NotFoundException, CannotCompileException {
  StringBuilder src=new StringBuilder(" if (deployed) {");
  src.append("ClassLoader curCl = Thread.currentThread().getContextClassLoader();");
  src.append(PluginManagerInvoker.buildInitializePlugin(OwbPlugin.class,"curCl"));
  src.append(PluginManagerInvoker.buildCallPluginMethod("curCl",OwbPlugin.class,"init"));
  src.append(PluginManagerInvoker.buildCallPluginMethod("curCl",OwbPlugin.class,"registerBeansXmls","$1.getBeanXmls()","java.util.Set"));
  src.append("}");
  CtMethod startApplication=clazz.getDeclaredMethod("deploy");
  startApplication.insertAfter(src.toString());
  LOGGER.debug("Class '{}' patched with OwbPlugin registration.",clazz.getName());
}
