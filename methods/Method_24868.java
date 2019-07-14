public static List<Edit> parseProgramImports(CharSequence source,List<ImportStatement> outImports){
  List<Edit> result=new ArrayList<>();
  Matcher matcher=IMPORT_REGEX.matcher(source);
  while (matcher.find()) {
    ImportStatement is=ImportStatement.parse(matcher.toMatchResult());
    outImports.add(is);
    int idx=matcher.start(1);
    int len=matcher.end(1) - idx;
    result.add(Edit.move(idx,len,0));
    result.add(Edit.insert(0,"\n"));
  }
  return result;
}
