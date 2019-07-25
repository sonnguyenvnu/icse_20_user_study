/** 
 * ???Neptune????
 * @param application  ???Appliction
 * @param config  ????
 */
public static void init(Application application,NeptuneConfig config){
  sHostContext=application;
  sGlobalConfig=config != null ? config : new NeptuneConfig.NeptuneConfigBuilder().build();
  PluginDebugLog.setIsDebug(sGlobalConfig.isDebug());
  boolean hookInstr=VersionUtils.hasPie() || sGlobalConfig.getSdkMode() != NeptuneConfig.LEGACY_MODE;
  if (hookInstr) {
    hookInstrumentation();
  }
  PluginPackageManagerNative.getInstance(sHostContext).setPackageInfoManager(sGlobalConfig.getPluginInfoProvider());
}
