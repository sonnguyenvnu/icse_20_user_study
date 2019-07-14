/** 
 * ??jdk????????????
 * @param moduleName ???(?????)
 * @return ?????linux64/???.????win32/???.???
 */
public static String getSysAndBit(String moduleName){
  String SysAndBit="";
  String extension=".dll";
  OS_NAME=OS_NAME.toLowerCase();
  if (OS_NAME.contains("win")) {
    if (OS_ARCH.contains("86")) {
      SysAndBit="win32/";
    }
 else {
      SysAndBit="win64/";
    }
  }
 else {
    extension=".so";
    System.out.println(OS_NAME);
    if (OS_ARCH.contains("86")) {
      SysAndBit="linux32/";
    }
 else {
      SysAndBit="linux64/";
    }
  }
  System.out.println(OS_NAME);
  System.out.println(OS_ARCH);
  return SysAndBit + moduleName + extension;
}
