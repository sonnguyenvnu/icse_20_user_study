/** 
 * ??AppInfo?Bean
 * @param pm ????
 * @param pi ????
 * @return AppInfo?
 */
private static AppInfo getBean(PackageManager pm,PackageInfo pi){
  ApplicationInfo ai=pi.applicationInfo;
  String name=ai.loadLabel(pm).toString();
  Drawable icon=ai.loadIcon(pm);
  String packageName=pi.packageName;
  String packagePath=ai.sourceDir;
  String versionName=pi.versionName;
  int versionCode=pi.versionCode;
  boolean isSD=(ApplicationInfo.FLAG_SYSTEM & ai.flags) != ApplicationInfo.FLAG_SYSTEM;
  boolean isUser=(ApplicationInfo.FLAG_SYSTEM & ai.flags) != ApplicationInfo.FLAG_SYSTEM;
  return new AppInfo(name,icon,packageName,packagePath,versionName,versionCode,isSD,isUser);
}
