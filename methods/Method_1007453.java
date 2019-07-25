@OnClassLoadEvent(classNameRegexp=WICKET_APPLICATION) public static void init(CtClass ctClass) throws NotFoundException, CannotCompileException {
  String src=PluginManagerInvoker.buildInitializePlugin(WicketPlugin.class);
  src+=PluginManagerInvoker.buildCallPluginMethod(WicketPlugin.class,"registerApplication","this","java.lang.Object");
  ctClass.getDeclaredConstructor(new CtClass[0]).insertAfter(src);
  LOGGER.info("Wicket application has been enhanced.");
}
