public static String commaSeparatedTerseNamesForLanguageVersion(List<LanguageVersion> languageVersions){
  if (languageVersions == null || languageVersions.isEmpty()) {
    return "";
  }
  StringBuilder builder=new StringBuilder();
  builder.append(languageVersions.get(0).getTerseName());
  for (int i=1; i < languageVersions.size(); i++) {
    builder.append(", ").append(languageVersions.get(i).getTerseName());
  }
  return builder.toString();
}
