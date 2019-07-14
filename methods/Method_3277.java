/** 
 * ???????????
 * @param path ?
 */
public void calcCost(Path path){
  path.cost=0.0;
  if (alphaFloat_ != null) {
    float c=0.0f;
    for (int i=0; path.fvector.get(i) != -1; i++) {
      c+=alphaFloat_[path.fvector.get(i) + path.lnode.y * y_.size() + path.rnode.y];
    }
    path.cost=costFactor_ * c;
  }
 else {
    double c=0.0;
    for (int i=0; path.fvector.get(i) != -1; i++) {
      c+=alpha_[path.fvector.get(i) + path.lnode.y * y_.size() + path.rnode.y];
    }
    path.cost=costFactor_ * c;
  }
}
