public String getPackageResourcePath(){
  int myUid=Process.myUid();
  ApplicationInfo appInfo=this.mPackage.applicationInfo;
  return appInfo.uid == myUid ? appInfo.sourceDir : appInfo.publicSourceDir;
}
