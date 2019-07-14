/** 
 * ??????????? 1.?buff?????this.cursor????? 2.?map??????????results 3.?map?????CJDK?????????results
 */
void outputToResult(){
  int index=0;
  for (; index <= this.cursor; ) {
    if (CharacterUtil.CHAR_USELESS == this.charTypes[index]) {
      index++;
      continue;
    }
    LexemePath path=this.pathMap.get(index);
    if (path != null) {
      Lexeme l=path.pollFirst();
      while (l != null) {
        this.results.add(l);
        index=l.getBegin() + l.getLength();
        l=path.pollFirst();
        if (l != null) {
          for (; index < l.getBegin(); index++) {
            this.outputSingleCJK(index);
          }
        }
      }
    }
 else {
      this.outputSingleCJK(index);
      index++;
    }
  }
  this.pathMap.clear();
}
