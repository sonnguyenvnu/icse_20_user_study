private static String supportedVersions(){
  return "Languages and version suported:" + PMD.EOL + LanguageRegistry.commaSeparatedTerseNamesForLanguage(LanguageRegistry.findWithRuleSupport()) + PMD.EOL;
}
