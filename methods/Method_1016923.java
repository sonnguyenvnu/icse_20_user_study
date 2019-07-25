/** 
 * Adds classification results to the ROC data
 * @param trial Trial results to add to ROC data
 */
public void add(Classification classification){
  int correctIndex=classification.getInstance().getLabeling().getBestIndex();
  LabelVector lv=classification.getLabelVector();
  double[] values=lv.getValues();
  if (!Alphabet.alphabetsMatch(this,lv)) {
    throw new IllegalArgumentException("Alphabets do not match");
  }
  int numLabels=this.labelAlphabet.size();
  for (int label=0; label < numLabels; label++) {
    double labelValue=values[label];
    int[][] thresholdCounts=this.counts[label];
    int threshold=0;
    for (; threshold < this.thresholds.length && labelValue >= this.thresholds[threshold]; threshold++) {
      if (correctIndex == label) {
        thresholdCounts[threshold][TRUE_POSITIVE]++;
      }
 else {
        thresholdCounts[threshold][FALSE_POSITIVE]++;
      }
    }
    for (; threshold < this.thresholds.length; threshold++) {
      if (correctIndex == label) {
        thresholdCounts[threshold][FALSE_NEGATIVE]++;
      }
 else {
        thresholdCounts[threshold][TRUE_NEGATIVE]++;
      }
    }
  }
}
