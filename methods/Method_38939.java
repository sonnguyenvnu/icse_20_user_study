/** 
 * Emits a comment. Also checks for conditional comments!
 */
protected void emitComment(final int from,final int to){
  if (config.enableConditionalComments) {
    if (match(CC_IF,from)) {
      int endBracketNdx=find(']',from + 3,to);
      CharSequence expression=charSequence(from + 1,endBracketNdx);
      ndx=endBracketNdx + 1;
      char c=input[ndx];
      if (c != '>') {
        errorInvalidToken();
      }
      visitor.condComment(expression,true,true,false);
      state=DATA_STATE;
      return;
    }
    if (to > CC_ENDIF2.length && match(CC_ENDIF2,to - CC_ENDIF2.length)) {
      visitor.condComment(_ENDIF,false,true,true);
      state=DATA_STATE;
      return;
    }
  }
  CharSequence comment=charSequence(from,to);
  visitor.comment(comment);
  commentStart=-1;
}
