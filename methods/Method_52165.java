private boolean basicEquivalence(Node node1,Node node2,String varName1,String varName2){
  if (node1.getClass() != node2.getClass()) {
    return false;
  }
  String image1=node1.getImage();
  String image2=node2.getImage();
  return Objects.equals(image1,image2) || Objects.equals(image1,varName1) && Objects.equals(image2,varName2) && isNoMethodName(node1) && isNoMethodName(node2);
}
