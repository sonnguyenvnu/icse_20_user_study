/** 
 * Promotes (by  {@link #alpha alpha}) the weights  responsible for the incorrect guess
 * @param lpos index of incorrectly guessed label
 * @param fv feature vector
 */
private void promote(int lpos,FeatureVector fv){
  int fvisize=fv.numLocations();
  for (int fvi=0; fvi < fvisize; fvi++) {
    int fi=fv.indexAtLocation(fvi);
    this.weights[lpos][fi]*=this.alpha;
  }
}
