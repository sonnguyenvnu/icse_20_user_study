/** 
 * Use scanner to find keywords, strings and comments.
 */
private void highlightLexicalTokens(){
  RecognizerSharedState state=new RecognizerSharedState();
  state.errorRecovery=true;
  PythonLexer lex=new PythonLexer(new ANTLRStringStream(source){
    @Override public String getSourceName(){
      return path;
    }
  }
,state);
  lex.setErrorHandler(new RecordingErrorHandler(){
    @Override public void error(    String message,    PythonTree t){
    }
    @Override public void reportError(    BaseRecognizer br,    RecognitionException re){
    }
  }
);
  Token tok;
  while ((tok=lex.nextToken()) != Token.EOF_TOKEN) {
switch (tok.getType()) {
case PythonLexer.STRING:
{
        int beg=((CommonToken)tok).getStartIndex();
        int end=((CommonToken)tok).getStopIndex();
        if (!docOffsets.contains(beg)) {
          addStyle(beg,end - beg + 1,StyleRun.Type.STRING);
        }
        break;
      }
case PythonLexer.COMMENT:
{
      int beg=((CommonToken)tok).getStartIndex();
      int end=((CommonToken)tok).getStopIndex();
      String comment=tok.getText();
      addStyle(beg,end - beg + 1,StyleRun.Type.COMMENT);
      break;
    }
case PythonLexer.AND:
case PythonLexer.AS:
case PythonLexer.ASSERT:
case PythonLexer.BREAK:
case PythonLexer.CLASS:
case PythonLexer.CONTINUE:
case PythonLexer.DEF:
case PythonLexer.DELETE:
case PythonLexer.ELIF:
case PythonLexer.EXCEPT:
case PythonLexer.FINALLY:
case PythonLexer.FOR:
case PythonLexer.FROM:
case PythonLexer.GLOBAL:
case PythonLexer.IF:
case PythonLexer.IMPORT:
case PythonLexer.IN:
case PythonLexer.IS:
case PythonLexer.LAMBDA:
case PythonLexer.NOT:
case PythonLexer.OR:
case PythonLexer.ORELSE:
case PythonLexer.PASS:
case PythonLexer.PRINT:
case PythonLexer.RAISE:
case PythonLexer.RETURN:
case PythonLexer.TRY:
case PythonLexer.WHILE:
case PythonLexer.WITH:
case PythonLexer.YIELD:
{
    int beg=((CommonToken)tok).getStartIndex();
    int end=((CommonToken)tok).getStopIndex();
    addStyle(beg,end - beg + 1,StyleRun.Type.KEYWORD);
    break;
  }
}
}
}
