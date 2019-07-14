/** 
 * Returns all available SD-Cards in the system (include emulated) <p> Warning: Hack! Based on Android source code of version 4.3 (API 18) Because there is no standard way to get it. TODO: Test on future Android versions 4.4+
 * @return paths to all available SD-Cards in the system (include emulated)
 */
public synchronized ArrayList<String> getStorageDirectories(){
  final ArrayList<String> rv=new ArrayList<>();
  final String rawExternalStorage=System.getenv("EXTERNAL_STORAGE");
  final String rawSecondaryStoragesStr=System.getenv("SECONDARY_STORAGE");
  final String rawEmulatedStorageTarget=System.getenv("EMULATED_STORAGE_TARGET");
  if (TextUtils.isEmpty(rawEmulatedStorageTarget)) {
    if (TextUtils.isEmpty(rawExternalStorage)) {
      if (new File(DEFAULT_FALLBACK_STORAGE_PATH).exists()) {
        rv.add(DEFAULT_FALLBACK_STORAGE_PATH);
      }
 else {
        rv.add(Environment.getExternalStorageDirectory().getAbsolutePath());
      }
    }
 else {
      rv.add(rawExternalStorage);
    }
  }
 else {
    final String rawUserId;
    if (SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR1) {
      rawUserId="";
    }
 else {
      final String path=Environment.getExternalStorageDirectory().getAbsolutePath();
      final String[] folders=DIR_SEPARATOR.split(path);
      final String lastFolder=folders[folders.length - 1];
      boolean isDigit=false;
      try {
        Integer.valueOf(lastFolder);
        isDigit=true;
      }
 catch (      NumberFormatException ignored) {
      }
      rawUserId=isDigit ? lastFolder : "";
    }
    if (TextUtils.isEmpty(rawUserId)) {
      rv.add(rawEmulatedStorageTarget);
    }
 else {
      rv.add(rawEmulatedStorageTarget + File.separator + rawUserId);
    }
  }
  if (!TextUtils.isEmpty(rawSecondaryStoragesStr)) {
    final String[] rawSecondaryStorages=rawSecondaryStoragesStr.split(File.pathSeparator);
    Collections.addAll(rv,rawSecondaryStorages);
  }
  if (SDK_INT >= Build.VERSION_CODES.M && checkStoragePermission())   rv.clear();
  if (SDK_INT >= Build.VERSION_CODES.KITKAT) {
    String strings[]=FileUtil.getExtSdCardPathsForActivity(this);
    for (    String s : strings) {
      File f=new File(s);
      if (!rv.contains(s) && FileUtils.canListFiles(f))       rv.add(s);
    }
  }
  if (isRootExplorer()) {
    rv.add("/");
  }
  File usb=getUsbDrive();
  if (usb != null && !rv.contains(usb.getPath()))   rv.add(usb.getPath());
  if (SDK_INT >= Build.VERSION_CODES.KITKAT) {
    if (SingletonUsbOtg.getInstance().isDeviceConnected()) {
      rv.add(OTGUtil.PREFIX_OTG + "/");
    }
  }
  return rv;
}
