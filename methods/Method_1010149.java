/** 
 * Record MLs into checkpoint state, assuming output nodes of the mappings are from the model being marked as 'checkpoint', and input nodes being traced with transitionTrace
 */
public void export(CheckpointStateBuilder cp){
  for (  Entry<String,Map<SNode,Object>> o : myMappingNameAndInputNodeToOutputNodeMap.entrySet()) {
    String label=o.getKey();
    for (    Entry<SNode,Object> i : o.getValue().entrySet()) {
      SNode inputNode=i.getKey();
      if (inputNode == null) {
        continue;
      }
      Object value=i.getValue();
      if (value instanceof SNode) {
        cp.record(inputNode,label,(SNode)value);
      }
 else       if (value instanceof Collection) {
        @SuppressWarnings("unchecked") Collection<SNode> collection=(Collection<SNode>)value;
        cp.record(inputNode,label,collection);
      }
    }
  }
  myConditionalRoots.forEach(p -> cp.record(p.o1,p.o2));
}
