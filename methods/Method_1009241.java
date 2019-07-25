public void push(Quote quote,String quotePromoter,int currentLexicalState){
  StackFrame stackFrame=new StackFrame(quote,quotePromoter,currentLexicalState);
  push(stackFrame);
}
