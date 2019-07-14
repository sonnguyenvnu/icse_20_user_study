/** 
 * The braceStack contains all nodes which are important to link the data flow nodes. The cbrStack contains continue, break, and return nodes. There are 2 Stacks because the have to process differently.
 */
public void pushOnStack(NodeType type,DataFlowNode node){
  StackObject obj=new StackObject(type,node);
  if (type == NodeType.RETURN_STATEMENT || type == NodeType.BREAK_STATEMENT || type == NodeType.CONTINUE_STATEMENT || type == NodeType.THROW_STATEMENT) {
    continueBreakReturnStack.push(obj);
    tryToLog("continueBreakReturnStack",node);
  }
 else {
    braceStack.push(obj);
    tryToLog("braceStack",node);
  }
  node.setType(type);
}
