/** 
 * This class encapsulates the access to the DataFlowNode class. Is this worthwhile? TODO I think it's too confusing to have the DataFlowNode constructor add the created instance to the List. I think it'd be clearer if we did that more "procedurally", i.e., create the object, then add it to the list.
 */
public DataFlowNode createNewNode(Node node){
  return dataFlowHandler.createDataFlowNode(dataFlow,node);
}
