/** 
 * ???????????
 * @param node
 */
public void calcCost(Node node){
  node.cost=0.0;
  if (alphaFloat_ != null) {
    float c=0.0f;
    for (int i=0; node.fVector.get(i) != -1; i++) {
      c+=alphaFloat_[node.fVector.get(i) + node.y];
    }
    node.cost=costFactor_ * c;
  }
 else {
    double c=0.0;
    for (int i=0; node.fVector.get(i) != -1; i++) {
      c+=alpha_[node.fVector.get(i) + node.y];
    }
    node.cost=costFactor_ * c;
  }
}
