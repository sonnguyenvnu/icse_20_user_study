@Override public void visitTryCatchBlock(final Label start,final Label end,final Label handler,final String type){
  Handler newHandler=new Handler(start,end,handler,type != null ? symbolTable.addConstantClass(type).index : 0,type);
  if (firstHandler == null) {
    firstHandler=newHandler;
  }
 else {
    lastHandler.nextHandler=newHandler;
  }
  lastHandler=newHandler;
}
