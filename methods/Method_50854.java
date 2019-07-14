public static String buildUsageText(JCommander jcommander){
  StringBuilder usage=new StringBuilder();
  String allCommandsDescription=null;
  if (jcommander != null && jcommander.getCommands() != null) {
    for (    String command : jcommander.getCommands().keySet()) {
      allCommandsDescription+=jcommander.getCommandDescription(command) + PMD.EOL;
    }
  }
  String fullText=PMD.EOL + "Mandatory arguments:" + PMD.EOL + "1) A java source code filename or directory" + PMD.EOL + "2) A report format " + PMD.EOL + "3) A ruleset filename or a comma-delimited string of ruleset filenames" + PMD.EOL + PMD.EOL + "For example: " + PMD.EOL + getWindowsLaunchCmd() + " -d c:\\my\\source\\code -f html -R java-unusedcode" + PMD.EOL + PMD.EOL;
  fullText+=supportedVersions() + PMD.EOL;
  if (allCommandsDescription != null) {
    fullText+="Optional arguments that may be put before or after the mandatory arguments: " + PMD.EOL + allCommandsDescription + PMD.EOL;
  }
  fullText+="Available report formats and their configuration properties are:" + PMD.EOL + getReports() + PMD.EOL + getExamples() + PMD.EOL + PMD.EOL + PMD.EOL;
  fullText+=usage.toString();
  return fullText;
}
