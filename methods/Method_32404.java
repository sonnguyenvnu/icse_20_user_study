private static PeriodFormatter buildWordBased(Locale locale){
  ResourceBundle b=ResourceBundle.getBundle(BUNDLE_NAME,locale);
  if (containsKey(b,"PeriodFormat.regex.separator")) {
    return buildRegExFormatter(b,locale);
  }
 else {
    return buildNonRegExFormatter(b,locale);
  }
}
