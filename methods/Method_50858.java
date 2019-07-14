private static String getUnixExample(){
  final String launchCmd="$ pmd-bin-" + PMDVersion.VERSION + "/bin/run.sh pmd";
  return "For example on *nix: " + PMD.EOL + launchCmd + " -dir /home/workspace/src/main/java/code -f html -rulesets rulesets/java/quickstart.xml,category/java/codestyle.xml" + PMD.EOL + launchCmd + " -d ./src/main/java/code -R rulesets/java/quickstart.xml -f xslt -property xsltFilename=my-own.xsl" + PMD.EOL + launchCmd + " -d ./src/main/java/code -f html -R rulesets/java/quickstart.xml -auxclasspath commons-collections.jar:derby.jar" + PMD.EOL;
}
