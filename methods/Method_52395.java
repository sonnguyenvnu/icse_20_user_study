private int processBlocks(Map<Node,Map<Node,Integer>> blocks){
  int anticipatedLength=0;
  int ifLength=0;
  for (  Map.Entry<Node,Map<Node,Integer>> entry : blocks.entrySet()) {
    ifLength=0;
    for (    Map.Entry<Node,Integer> entry2 : entry.getValue().entrySet()) {
      Integer value=entry2.getValue();
      ifLength=Math.max(ifLength,value.intValue());
    }
    anticipatedLength+=ifLength;
  }
  return anticipatedLength;
}
