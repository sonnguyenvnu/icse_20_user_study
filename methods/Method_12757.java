private static Resources createResourcesSimple(Context hostContext,String apk) throws Exception {
  Resources hostResources=hostContext.getResources();
  Resources newResources=null;
  AssetManager assetManager;
  Reflector reflector=Reflector.on(AssetManager.class).method("addAssetPath",String.class);
  if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
    assetManager=AssetManager.class.newInstance();
    reflector.bind(assetManager);
    final int cookie1=reflector.call(hostContext.getApplicationInfo().sourceDir);
    ;
    if (cookie1 == 0) {
      throw new RuntimeException("createResources failed, can't addAssetPath for " + hostContext.getApplicationInfo().sourceDir);
    }
  }
 else {
    assetManager=hostResources.getAssets();
    reflector.bind(assetManager);
  }
  final int cookie2=reflector.call(apk);
  if (cookie2 == 0) {
    throw new RuntimeException("createResources failed, can't addAssetPath for " + apk);
  }
  List<LoadedPlugin> pluginList=PluginManager.getInstance(hostContext).getAllLoadedPlugins();
  for (  LoadedPlugin plugin : pluginList) {
    final int cookie3=reflector.call(plugin.getLocation());
    if (cookie3 == 0) {
      throw new RuntimeException("createResources failed, can't addAssetPath for " + plugin.getLocation());
    }
  }
  if (isMiUi(hostResources)) {
    newResources=MiUiResourcesCompat.createResources(hostResources,assetManager);
  }
 else   if (isVivo(hostResources)) {
    newResources=VivoResourcesCompat.createResources(hostContext,hostResources,assetManager);
  }
 else   if (isNubia(hostResources)) {
    newResources=NubiaResourcesCompat.createResources(hostResources,assetManager);
  }
 else   if (isNotRawResources(hostResources)) {
    newResources=AdaptationResourcesCompat.createResources(hostResources,assetManager);
  }
 else {
    newResources=new Resources(assetManager,hostResources.getDisplayMetrics(),hostResources.getConfiguration());
  }
  for (  LoadedPlugin plugin : pluginList) {
    plugin.updateResources(newResources);
  }
  return newResources;
}
