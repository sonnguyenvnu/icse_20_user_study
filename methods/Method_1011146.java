public void execute(SNode node){
  new ClassLikeMethodChecker().checkMethod(((SNode)FixMethodAutomatically_QuickFix.this.getField("method")[0]),new ClassLikeMethodFixer());
}
