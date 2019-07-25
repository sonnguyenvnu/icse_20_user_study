/** 
 * Adds existing ROC data to this ROC data
 * @param rocData ROC data to add
 */
public void add(ROCData rocData){
  if (!Alphabet.alphabetsMatch(this,rocData)) {
    throw new IllegalArgumentException("Alphabets do not match");
  }
  if (!Arrays.equals(this.thresholds,rocData.thresholds)) {
    throw new IllegalArgumentException("Thresholds do not match");
  }
  int countsLength=this.counts.length;
  for (int c=0; c < countsLength; c++) {
    int[][] thisClassCounts=this.counts[c];
    int[][] otherClassCounts=rocData.counts[c];
    int classLength=thisClassCounts.length;
    for (int t=0; t < classLength; t++) {
      int[] thisThrCounts=thisClassCounts[t];
      int[] otherThrCounts=otherClassCounts[t];
      int thrLength=thisThrCounts.length;
      for (int s=0; s < thrLength; s++) {
        thisThrCounts[s]+=otherThrCounts[s];
      }
    }
  }
}
