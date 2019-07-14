/** 
 * Use System Apis to update all existing resources. <br/> 1. Update ApplicationInfo.splitSourceDirs and LoadedApk.mSplitResDirs <br/> 2. Replace all keys of ResourcesManager.mResourceImpls to new ResourcesKey <br/> 3. Use ResourcesManager.appendLibAssetForMainAssetPath(appInfo.publicSourceDir, "${packageName}.vastub") to update all existing resources. <br/> see android.webkit.WebViewDelegate.addWebViewAssetPath(Context)
 */
@TargetApi(Build.VERSION_CODES.N) private static Resources createResourcesForN(Context context,String packageName,File apk) throws Exception {
  long startTime=System.currentTimeMillis();
  String newAssetPath=apk.getAbsolutePath();
  ApplicationInfo info=context.getApplicationInfo();
  String baseResDir=info.publicSourceDir;
  info.splitSourceDirs=append(info.splitSourceDirs,newAssetPath);
  LoadedApk loadedApk=Reflector.with(context).field("mPackageInfo").get();
  Reflector rLoadedApk=Reflector.with(loadedApk).field("mSplitResDirs");
  String[] splitResDirs=rLoadedApk.get();
  rLoadedApk.set(append(splitResDirs,newAssetPath));
  final android.app.ResourcesManager resourcesManager=android.app.ResourcesManager.getInstance();
  ArrayMap<ResourcesKey,WeakReference<ResourcesImpl>> originalMap=Reflector.with(resourcesManager).field("mResourceImpls").get();
synchronized (resourcesManager) {
    HashMap<ResourcesKey,WeakReference<ResourcesImpl>> resolvedMap=new HashMap<>();
    if (Build.VERSION.SDK_INT >= 28 || (Build.VERSION.SDK_INT == 27 && Build.VERSION.PREVIEW_SDK_INT != 0)) {
      ResourcesManagerCompatForP.resolveResourcesImplMap(originalMap,resolvedMap,context,loadedApk);
    }
 else {
      ResourcesManagerCompatForN.resolveResourcesImplMap(originalMap,resolvedMap,baseResDir,newAssetPath);
    }
    originalMap.clear();
    originalMap.putAll(resolvedMap);
  }
  android.app.ResourcesManager.getInstance().appendLibAssetForMainAssetPath(baseResDir,packageName + ".vastub");
  Resources newResources=context.getResources();
  for (  LoadedPlugin plugin : PluginManager.getInstance(context).getAllLoadedPlugins()) {
    plugin.updateResources(newResources);
  }
  Log.d(TAG,"createResourcesForN cost time: +" + (System.currentTimeMillis() - startTime) + "ms");
  return newResources;
}
