public void load(List<StringLocated> input){
  for (  StringLocated s : input) {
    final TLineType type=TLineType.getFromLine(s.getTrimmed().getString());
    try {
      context.executeOneLine(global,type,s,null);
    }
 catch (    EaterException e) {
      context.getResult().add(s.withErrorPreprocessor(e.getMessage()));
      this.result=context.getResult();
      changeLastLine(context.getDebug(),e.getMessage());
      this.preprocessorError=true;
      return;
    }
  }
  this.result=context.getResult();
}
