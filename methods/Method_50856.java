private static String getWindowsLaunchCmd(){
  final String WINDOWS_PROMPT="C:\\>";
  final String launchCmd="pmd-bin-" + PMDVersion.VERSION + "\\bin\\pmd.bat";
  return WINDOWS_PROMPT + launchCmd;
}
