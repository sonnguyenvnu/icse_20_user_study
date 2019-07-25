/** 
 * {@inheritDoc}
 * @return a list of detected font files
 */
public List find(){
  List fontDirList=new java.util.ArrayList();
  String windir=null;
  try {
    windir=System.getProperty("env.windir");
  }
 catch (  SecurityException e) {
  }
  String osName=System.getProperty("os.name");
  if (windir == null) {
    try {
      windir=getWinDir(osName);
    }
 catch (    IOException e) {
    }
  }
  File osFontsDir=null, psFontsDir=null;
  if (windir != null) {
    if (windir.endsWith("/")) {
      windir=windir.substring(0,windir.length() - 1);
    }
    osFontsDir=new File(windir + File.separator + "FONTS");
    if (osFontsDir.exists() && osFontsDir.canRead()) {
      fontDirList.add(osFontsDir);
    }
    psFontsDir=new File(windir.substring(0,2) + File.separator + "PSFONTS");
    if (psFontsDir.exists() && psFontsDir.canRead()) {
      fontDirList.add(psFontsDir);
    }
  }
 else {
    String windowsDirName=osName.endsWith("NT") ? "WINNT" : "WINDOWS";
    for (char driveLetter='C'; driveLetter <= 'E'; driveLetter++) {
      osFontsDir=new File(driveLetter + ":" + File.separator + windowsDirName + File.separator + "FONTS");
      if (osFontsDir.exists() && osFontsDir.canRead()) {
        fontDirList.add(osFontsDir);
        break;
      }
    }
    for (char driveLetter='C'; driveLetter <= 'E'; driveLetter++) {
      psFontsDir=new File(driveLetter + ":" + File.separator + "PSFONTS");
      if (psFontsDir.exists() && psFontsDir.canRead()) {
        fontDirList.add(psFontsDir);
        break;
      }
    }
  }
  return fontDirList;
}
