@Override public Object visit(ASTVariableDeclaratorId node,Object data){
  if (!isStringBuilderOrBuffer(node)) {
    return data;
  }
  threshold=getProperty(THRESHOLD_DESCRIPTOR);
  int concurrentCount=checkConstructor(node,data);
  if (hasInitializer(node)) {
    concurrentCount+=checkInitializerExpressions(node);
  }
  Node lastBlock=getFirstParentBlock(node);
  Node currentBlock=lastBlock;
  Node rootNode=null;
  if (concurrentCount >= 1) {
    rootNode=node;
  }
  List<NameOccurrence> usages=determineUsages(node);
  for (  NameOccurrence no : usages) {
    JavaNameOccurrence jno=(JavaNameOccurrence)no;
    Node n=jno.getLocation();
    currentBlock=getFirstParentBlock(n);
    if (InefficientStringBufferingRule.isInStringBufferOperation(n,3,"append")) {
      ASTPrimaryExpression s=n.getFirstParentOfType(ASTPrimaryExpression.class);
      int numChildren=s.jjtGetNumChildren();
      for (int jx=0; jx < numChildren; jx++) {
        Node sn=s.jjtGetChild(jx);
        if (!(sn instanceof ASTPrimarySuffix) || sn.getImage() != null) {
          continue;
        }
        if (currentBlock != null && lastBlock != null && !currentBlock.equals(lastBlock) || currentBlock == null ^ lastBlock == null) {
          checkForViolation(rootNode,data,concurrentCount);
          concurrentCount=0;
        }
        if (concurrentCount == 0) {
          rootNode=sn;
        }
        if (isAdditive(sn)) {
          concurrentCount=processAdditive(data,concurrentCount,sn,rootNode);
          if (concurrentCount != 0) {
            rootNode=sn;
          }
        }
 else         if (!isAppendingStringLiteral(sn)) {
          checkForViolation(rootNode,data,concurrentCount);
          concurrentCount=0;
        }
 else {
          concurrentCount++;
        }
        lastBlock=currentBlock;
      }
    }
 else     if (n.getImage().endsWith(".toString") || n.getImage().endsWith(".length")) {
    }
 else {
      checkForViolation(rootNode,data,concurrentCount);
      concurrentCount=0;
    }
  }
  checkForViolation(rootNode,data,concurrentCount);
  return data;
}
