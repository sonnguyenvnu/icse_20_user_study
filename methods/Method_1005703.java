public static void backup(Object thiz,ActivityThread activityThread,ApplicationInfo aInfo,CompatibilityInfo compatInfo,ClassLoader baseLoader,boolean securityViolation,boolean includeCode,boolean registerPackage) throws Throwable {
  SandHook.callOriginByBackup(backup,thiz,activityThread,aInfo,compatInfo,baseLoader,securityViolation,includeCode,registerPackage);
}
