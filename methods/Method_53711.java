/** 
 * ???????????????
 * @param module ???(?????)
 * @return ?????linux64/???.????win32/???.???
 */
public static String getModulePath(String module){
  String folder="";
  String extension="";
  if (OS_NAME.contains("win")) {
    extension=".dll";
    if (OS_ARCH.contains("86")) {
      folder="win32/";
    }
 else {
      folder="win64/";
    }
  }
 else {
    extension=".so";
    if (OS_ARCH.contains("86")) {
      folder="linux32/";
    }
 else {
      folder="linux64/";
    }
  }
  return folder + module + extension;
}
