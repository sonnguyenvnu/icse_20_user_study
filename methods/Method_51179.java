public static String commaSeparatedTerseNamesForLanguage(List<Language> languages){
  StringBuilder builder=new StringBuilder();
  for (  Language language : languages) {
    if (builder.length() > 0) {
      builder.append(", ");
    }
    builder.append(language.getTerseName());
  }
  return builder.toString();
}
