/** 
 * @param neighbors
 * @return An array containing a score for each of the elements of <code>neighbors</code>.
 */
public double[] evaluate(Neighbor[] neighbors){
  double[] scores=new double[neighbors.length];
  LabelVector ranks=classifier.classify(neighbors).getLabelVector();
  for (int i=0; i < ranks.numLocations(); i++) {
    int idx=((Integer)ranks.getLabelAtRank(i).getEntry()).intValue();
    scores[idx]=ranks.getValueAtRank(i);
  }
  return scores;
}
