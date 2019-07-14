static private List<JavaProblem> checkForCurlyQuotes(PreprocessedSketch ps){
  List<JavaProblem> problems=new ArrayList<>(0);
  Matcher matcher=CURLY_QUOTE_REGEX.matcher(ps.scrubbedPdeCode);
  while (matcher.find()) {
    int pdeOffset=matcher.start();
    String q=matcher.group();
    int tabIndex=ps.pdeOffsetToTabIndex(pdeOffset);
    int tabOffset=ps.pdeOffsetToTabOffset(tabIndex,pdeOffset);
    int tabLine=ps.tabOffsetToTabLine(tabIndex,tabOffset);
    String message=Language.interpolate("editor.status.bad_curly_quote",q);
    JavaProblem problem=new JavaProblem(message,JavaProblem.ERROR,tabIndex,tabLine);
    problem.setPDEOffsets(tabOffset,tabOffset + 1);
    problems.add(problem);
  }
  List<JavaProblem> problems2=new ArrayList<>(0);
  IProblem[] iproblems=ps.compilationUnit.getProblems();
  for (  IProblem iproblem : iproblems) {
switch (iproblem.getID()) {
case IProblem.ParsingErrorDeleteToken:
case IProblem.ParsingErrorDeleteTokens:
case IProblem.ParsingErrorInvalidToken:
case IProblem.ParsingErrorReplaceTokens:
case IProblem.UnterminatedString:
      SketchInterval in=ps.mapJavaToSketch(iproblem);
    if (in == SketchInterval.BEFORE_START)     continue;
  String badCode=ps.getPdeCode(in);
matcher.reset(badCode);
while (matcher.find()) {
int offset=matcher.start();
String q=matcher.group();
int tabStart=in.startTabOffset + offset;
int tabStop=tabStart + 1;
if (problems.stream().noneMatch(p -> p.getStartOffset() == tabStart)) {
  int line=ps.tabOffsetToTabLine(in.tabIndex,tabStart);
  String message;
  if (iproblem.getID() == IProblem.UnterminatedString) {
    message=Language.interpolate("editor.status.unterm_string_curly",q);
  }
 else {
    message=Language.interpolate("editor.status.bad_curly_quote",q);
  }
  JavaProblem p=new JavaProblem(message,JavaProblem.ERROR,in.tabIndex,line);
  p.setPDEOffsets(tabStart,tabStop);
  problems2.add(p);
}
}
}
}
problems.addAll(problems2);
return problems;
}
