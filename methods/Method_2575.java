/** 
 * ???
 */
public void unSimplify(){
  if (sourceNode == null) {
    sourceNode=new MDAGNode(false);
    equivalenceClassMDAGNodeHashMap=new HashMap<MDAGNode,MDAGNode>();
    MDAGNode[] toNodeArray=new MDAGNode[mdagDataArray.length];
    createMDAGNode(simplifiedSourceNode,-1,toNodeArray,new MDAGNode[mdagDataArray.length]);
    for (    MDAGNode mdagNode : toNodeArray) {
      equivalenceClassMDAGNodeHashMap.put(mdagNode,mdagNode);
    }
    simplifiedSourceNode=null;
  }
}
