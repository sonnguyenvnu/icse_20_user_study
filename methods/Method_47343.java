public static boolean isPathAccessible(String dir,SharedPreferences pref){
  File f=new File(dir);
  boolean showIfHidden=pref.getBoolean(PreferencesConstants.PREFERENCE_SHOW_HIDDENFILES,false), isDirSelfOrParent=dir.endsWith("/.") || dir.endsWith("/.."), showIfRoot=pref.getBoolean(PreferencesConstants.PREFERENCE_ROOTMODE,false);
  return f.exists() && f.isDirectory() && (!f.isHidden() || (showIfHidden && !isDirSelfOrParent)) && (!isRoot(dir) || showIfRoot);
}
