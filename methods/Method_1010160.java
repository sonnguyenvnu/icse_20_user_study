public List<SNode[]> partition(){
  int[][] partitions=GraphUtil.components(GraphUtil.removeOrientation(myDependencies));
  List<SNode[]> result=new ArrayList<>(partitions.length + 1);
  for (  int[] partition : partitions) {
    SNode[] proots=new SNode[partition.length];
    for (int e=0; e < proots.length; e++) {
      proots[e]=myRoots[partition[e]];
    }
    result.add(proots);
  }
  result.add(new SNode[0]);
  return result;
}
