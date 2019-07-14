private static String buildLanguageString(Format format){
  return TextUtils.isEmpty(format.language) || "und".equals(format.language) ? "" : format.language;
}
