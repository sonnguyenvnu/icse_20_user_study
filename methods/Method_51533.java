private void throwUnknownLanguageVersionException(String minOrMax,String unknownVersion){
  throw new IllegalArgumentException("Unknown " + minOrMax + " Language Version '" + unknownVersion + "' for Language '" + language.getTerseName() + "' for Rule " + name + "; supported Language Versions are: " + LanguageRegistry.commaSeparatedTerseNamesForLanguageVersion(language.getVersions()));
}
