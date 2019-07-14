@Override public AppsDataPair loadInBackground(){
  List<ApplicationInfo> apps=packageManager.getInstalledApplications(PackageManager.MATCH_UNINSTALLED_PACKAGES | PackageManager.MATCH_DISABLED_UNTIL_USED_COMPONENTS);
  if (apps == null)   return new AppsDataPair(Collections.emptyList(),Collections.emptyList());
  mApps=new AppsDataPair(new ArrayList<>(apps.size()),new ArrayList<>(apps.size()));
  for (  ApplicationInfo object : apps) {
    File sourceDir=new File(object.sourceDir);
    String label=object.loadLabel(packageManager).toString();
    PackageInfo info;
    try {
      info=packageManager.getPackageInfo(object.packageName,0);
    }
 catch (    PackageManager.NameNotFoundException e) {
      e.printStackTrace();
      info=null;
    }
    AppDataParcelable elem=new AppDataParcelable(label == null ? object.packageName : label,object.sourceDir,object.packageName,object.flags + "_" + (info != null ? info.versionName : ""),Formatter.formatFileSize(getContext(),sourceDir.length()),sourceDir.length(),sourceDir.lastModified());
    mApps.first.add(elem);
    Collections.sort(mApps.first,new AppDataParcelable.AppDataSorter(sortBy,asc));
    for (    AppDataParcelable p : mApps.first) {
      mApps.second.add(p.path);
    }
  }
  return mApps;
}
