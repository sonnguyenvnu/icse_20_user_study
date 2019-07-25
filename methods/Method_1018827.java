/** 
 * ??apk?????Dex??
 */
List<? extends File> load(boolean forceLoad) throws IOException {
  List<ExtractedDex> dexFiles;
  String prefsKeyPrefix=pkgName + ".";
  if (!forceLoad && !isModified(sourceApk,sourceCrc,prefsKeyPrefix)) {
    try {
      dexFiles=loadExistingExtractions(prefsKeyPrefix);
    }
 catch (    IOException ioe) {
      PluginDebugLog.runtimeLog(TAG,"Failed to reload existing extracted secondary dex files," + " falling back to fresh extraction" + ioe.getMessage());
      dexFiles=performExtractions();
      storeApkInfo(prefsKeyPrefix,getTimeStamp(sourceApk),sourceCrc,dexFiles);
    }
  }
 else {
    dexFiles=performExtractions();
    storeApkInfo(prefsKeyPrefix,getTimeStamp(sourceApk),sourceCrc,dexFiles);
  }
  return dexFiles;
}
