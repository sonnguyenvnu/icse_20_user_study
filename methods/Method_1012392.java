public List<IAppLife> parse(){
  List<IAppLife> modules=new ArrayList<>();
  try {
    ApplicationInfo appInfo=context.getPackageManager().getApplicationInfo(context.getPackageName(),PackageManager.GET_META_DATA);
    if (appInfo.metaData != null) {
      for (      String key : appInfo.metaData.keySet()) {
        if (MODULE_VALUE.equals(appInfo.metaData.get(key))) {
          modules.add(parseModule(key));
        }
      }
    }
  }
 catch (  PackageManager.NameNotFoundException e) {
    throw new RuntimeException("??Application??",e);
  }
  CommonLogger.e("module:" + modules.size());
  return modules;
}
