/** 
 * ??????
 */
public static void installModules(){
  ExtensionLoader<Module> loader=ExtensionLoaderFactory.getExtensionLoader(Module.class);
  String moduleLoadList=RpcConfigs.getStringValue(RpcOptions.MODULE_LOAD_LIST);
  for (  Map.Entry<String,ExtensionClass<Module>> o : loader.getAllExtensions().entrySet()) {
    String moduleName=o.getKey();
    Module module=o.getValue().getExtInstance();
    if (needLoad(moduleLoadList,moduleName)) {
      if (module.needLoad()) {
        if (LOGGER.isInfoEnabled()) {
          LOGGER.info("Install Module: {}",moduleName);
        }
        module.install();
        INSTALLED_MODULES.put(moduleName,module);
      }
 else {
        if (LOGGER.isInfoEnabled()) {
          LOGGER.info("The module " + moduleName + " does not need to be loaded.");
        }
      }
    }
 else {
      if (LOGGER.isInfoEnabled()) {
        LOGGER.info("The module " + moduleName + " is not in the module load list.");
      }
    }
  }
}
