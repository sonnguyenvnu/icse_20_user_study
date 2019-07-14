public static String buildUsageText(){
  String helpText=" For example on Windows:" + PMD.EOL;
  helpText+=" C:\\>" + "pmd-bin-" + PMDVersion.VERSION + "\\bin\\cpd.bat" + " --minimum-tokens 100 --files c:\\jdk18\\src\\java" + PMD.EOL;
  helpText+=PMD.EOL;
  helpText+=" For example on *nix:" + PMD.EOL;
  helpText+=" $ " + "pmd-bin-" + PMDVersion.VERSION + "/bin/run.sh cpd" + " --minimum-tokens 100 --files /path/to/java/code" + PMD.EOL;
  helpText+=PMD.EOL;
  helpText+=" Supported languages: " + Arrays.toString(LanguageFactory.supportedLanguages) + PMD.EOL;
  helpText+=" Formats: " + Arrays.toString(CPDConfiguration.getRenderers()) + PMD.EOL;
  return helpText;
}
