public static List<ImportStatement> parseProgramImports(CharSequence source){
  List<ImportStatement> result=new ArrayList<>();
  Matcher matcher=IMPORT_REGEX.matcher(source);
  while (matcher.find()) {
    ImportStatement is=ImportStatement.parse(matcher.toMatchResult());
    result.add(is);
  }
  return result;
}
