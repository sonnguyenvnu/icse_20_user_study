/** 
 * Is the current feature redundant? The current feature is determined by the current values in iterIndices, which tells us where we are in each PropertyList.Iterator. We do this test to ensure we only include the upper triange of conjunctions.
 * @param conjunctions conjunction array
 * @param j which offset we're on
 * @param iterIndices counters for each PropertyList.Iterator
 * @return true if feature is redundant
 */
private boolean redundant(int[][] conjunctions,int j,int[] iterIndices){
  for (int i=1; i < iterIndices.length; i++) {
    if (conjunctions[j][i - 1] == conjunctions[j][i] && iterIndices[i] <= iterIndices[i - 1])     return true;
  }
  return false;
}
