/** 
 * Basic WeldBeanDeploymentArchive transformation.
 * @param clazz
 * @param classPool
 * @throws NotFoundException
 * @throws CannotCompileException
 */
@OnClassLoadEvent(classNameRegexp="org.jboss.weld.environment.deployment.WeldBeanDeploymentArchive") public static void transform(CtClass clazz,ClassPool classPool) throws NotFoundException, CannotCompileException {
  StringBuilder src=new StringBuilder("{");
  src.append(PluginManagerInvoker.buildInitializePlugin(WeldPlugin.class));
  src.append(PluginManagerInvoker.buildCallPluginMethod(WeldPlugin.class,"init"));
  src.append("org.hotswap.agent.plugin.weld.command.BeanClassRefreshAgent.registerArchive(getClass().getClassLoader(), this, null);");
  src.append("}");
  for (  CtConstructor constructor : clazz.getDeclaredConstructors()) {
    constructor.insertAfter(src.toString());
  }
  LOGGER.debug("Class '{}' patched with BDA registration.",clazz.getName());
}
