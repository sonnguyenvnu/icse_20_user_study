/** 
 * ?????
 * @param instance   ??
 * @param guessLabel ????
 * @return
 */
public double viterbiDecode(Instance instance,int[] guessLabel){
  final int[] allLabel=featureMap.allLabels();
  final int bos=featureMap.bosTag();
  final int sentenceLength=instance.tagArray.length;
  final int labelSize=allLabel.length;
  int[][] preMatrix=new int[sentenceLength][labelSize];
  double[][] scoreMatrix=new double[2][labelSize];
  for (int i=0; i < sentenceLength; i++) {
    int _i=i & 1;
    int _i_1=1 - _i;
    int[] allFeature=instance.getFeatureAt(i);
    final int transitionFeatureIndex=allFeature.length - 1;
    if (0 == i) {
      allFeature[transitionFeatureIndex]=bos;
      for (int j=0; j < allLabel.length; j++) {
        preMatrix[0][j]=j;
        double score=score(allFeature,j);
        scoreMatrix[0][j]=score;
      }
    }
 else {
      for (int curLabel=0; curLabel < allLabel.length; curLabel++) {
        double maxScore=Integer.MIN_VALUE;
        for (int preLabel=0; preLabel < allLabel.length; preLabel++) {
          allFeature[transitionFeatureIndex]=preLabel;
          double score=score(allFeature,curLabel);
          double curScore=scoreMatrix[_i_1][preLabel] + score;
          if (maxScore < curScore) {
            maxScore=curScore;
            preMatrix[i][curLabel]=preLabel;
            scoreMatrix[_i][curLabel]=maxScore;
          }
        }
      }
    }
  }
  int maxIndex=0;
  double maxScore=scoreMatrix[(sentenceLength - 1) & 1][0];
  for (int index=1; index < allLabel.length; index++) {
    if (maxScore < scoreMatrix[(sentenceLength - 1) & 1][index]) {
      maxIndex=index;
      maxScore=scoreMatrix[(sentenceLength - 1) & 1][index];
    }
  }
  for (int i=sentenceLength - 1; i >= 0; --i) {
    guessLabel[i]=allLabel[maxIndex];
    maxIndex=preMatrix[i][maxIndex];
  }
  return maxScore;
}
