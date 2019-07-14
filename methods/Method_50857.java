private static String getWindowsExample(){
  final String launchCmd=getWindowsLaunchCmd();
  final String WINDOWS_PATH_TO_CODE="c:\\my\\source\\code ";
  return "For example on windows: " + PMD.EOL + launchCmd + " -dir " + WINDOWS_PATH_TO_CODE + "-format text -R rulesets/java/quickstart.xml -version 1.5 -language java -debug" + PMD.EOL + launchCmd + " -dir " + WINDOWS_PATH_TO_CODE + "-f xml -rulesets rulesets/java/quickstart.xml,category/java/codestyle.xml -encoding UTF-8" + PMD.EOL + launchCmd + " -d " + WINDOWS_PATH_TO_CODE + "-rulesets rulesets/java/quickstart.xml -auxclasspath lib\\commons-collections.jar;lib\\derby.jar" + PMD.EOL + launchCmd + " -d " + WINDOWS_PATH_TO_CODE + "-f html -R rulesets/java/quickstart.xml -auxclasspath file:///C:/my/classpathfile" + PMD.EOL + PMD.EOL;
}
