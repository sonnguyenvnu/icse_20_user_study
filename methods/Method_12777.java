private static boolean findAndCopyNativeLib(ZipFile zipfile,Context context,String cpuArch,PackageInfo packageInfo,File nativeLibDir) throws Exception {
  Log.d(TAG,"Try to copy plugin's cup arch: " + cpuArch);
  boolean findLib=false;
  boolean findSo=false;
  byte buffer[]=null;
  String libPrefix="lib/" + cpuArch + "/";
  ZipEntry entry;
  Enumeration e=zipfile.entries();
  while (e.hasMoreElements()) {
    entry=(ZipEntry)e.nextElement();
    String entryName=entry.getName();
    if (entryName.charAt(0) < 'l') {
      continue;
    }
    if (entryName.charAt(0) > 'l') {
      break;
    }
    if (!findLib && !entryName.startsWith("lib/")) {
      continue;
    }
    findLib=true;
    if (!entryName.endsWith(".so") || !entryName.startsWith(libPrefix)) {
      continue;
    }
    if (buffer == null) {
      findSo=true;
      Log.d(TAG,"Found plugin's cup arch dir: " + cpuArch);
      buffer=new byte[8192];
    }
    String libName=entryName.substring(entryName.lastIndexOf('/') + 1);
    Log.d(TAG,"verify so " + libName);
    File libFile=new File(nativeLibDir,libName);
    String key=packageInfo.packageName + "_" + libName;
    if (libFile.exists()) {
      int VersionCode=Settings.getSoVersion(context,key);
      if (VersionCode == packageInfo.versionCode) {
        Log.d(TAG,"skip existing so : " + entry.getName());
        continue;
      }
    }
    FileOutputStream fos=new FileOutputStream(libFile);
    Log.d(TAG,"copy so " + entry.getName() + " of " + cpuArch);
    copySo(buffer,zipfile.getInputStream(entry),fos);
    Settings.setSoVersion(context,key,packageInfo.versionCode);
  }
  if (!findLib) {
    Log.d(TAG,"Fast skip all!");
    return true;
  }
  return findSo;
}
