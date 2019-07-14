public BinaryNodeTravel createNode(){
  BinaryNodeTravel nodeA=new BinaryNodeTravel("A",null,null);
  BinaryNodeTravel nodeB=new BinaryNodeTravel("B",null,null);
  BinaryNodeTravel nodeC=new BinaryNodeTravel("C",null,null);
  BinaryNodeTravel nodeD=new BinaryNodeTravel("D",null,null);
  BinaryNodeTravel nodeE=new BinaryNodeTravel("E",null,null);
  BinaryNodeTravel nodeF=new BinaryNodeTravel("F",null,null);
  nodeA.setLeft(nodeB);
  nodeB.setLeft(nodeD);
  nodeA.setRight(nodeC);
  nodeC.setLeft(nodeE);
  nodeC.setRight(nodeF);
  return nodeA;
}
