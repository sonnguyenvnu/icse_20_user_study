/** 
 * ?????? //	 * @param orgLexemes
 * @param useSmart
 */
void process(AnalyzeContext context,boolean useSmart){
  QuickSortSet orgLexemes=context.getOrgLexemes();
  Lexeme orgLexeme=orgLexemes.pollFirst();
  LexemePath crossPath=new LexemePath();
  while (orgLexeme != null) {
    if (!crossPath.addCrossLexeme(orgLexeme)) {
      if (crossPath.size() == 1 || !useSmart) {
        context.addLexemePath(crossPath);
      }
 else {
        QuickSortSet.Cell headCell=crossPath.getHead();
        LexemePath judgeResult=this.judge(headCell,crossPath.getPathLength());
        context.addLexemePath(judgeResult);
      }
      crossPath=new LexemePath();
      crossPath.addCrossLexeme(orgLexeme);
    }
    orgLexeme=orgLexemes.pollFirst();
  }
  if (crossPath.size() == 1 || !useSmart) {
    context.addLexemePath(crossPath);
  }
 else {
    QuickSortSet.Cell headCell=crossPath.getHead();
    LexemePath judgeResult=this.judge(headCell,crossPath.getPathLength());
    context.addLexemePath(judgeResult);
  }
}
