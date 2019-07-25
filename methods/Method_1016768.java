/** 
 * Only evaluates over sequences which contain errors.  Examine region not directly corrected by <code>correctedSegments </code> to measure effects of error propagation.
 * @param model used to segment input sequence
 * @param predictions list of the corrected segmentation
 * @param ilist list of testing data
 * @param correctedSegments list of {@link Segment}s in each sequence that were corrected...currently only allows one segment per instance.
 * @param uncorrected true if we only evaluate sequences whereerrors remain after correction
 */
public void evaluate(Transducer model,ArrayList predictions,InstanceList ilist,ArrayList correctedSegments,String description,PrintStream outputStream,boolean errorsInUncorrected){
  if (predictions.size() != ilist.size() || correctedSegments.size() != ilist.size())   throw new IllegalArgumentException("number of predicted sequence (" + predictions.size() + ") and number of corrected segments (" + correctedSegments.size() + ") must be equal to length of instancelist (" + ilist.size() + ")");
  int numIncorrect2Correct=0;
  int numCorrect2Incorrect=0;
  int numPropagatedIncorrect2Correct=0;
  int numPredictedCorrect=0;
  int numCorrectedCorrect=0;
  int numUncorrectedCorrectBeforePropagation=0;
  int numUncorrectedCorrectAfterPropagation=0;
  int totalTokens=0;
  int totalTokensInUncorrectedRegion=0;
  int numCorrectedSequences=0;
  for (int i=0; i < ilist.size(); i++) {
    Instance instance=ilist.get(i);
    Sequence input=(Sequence)instance.getData();
    Sequence trueSequence=(Sequence)instance.getTarget();
    Sequence predSequence=(Sequence)new MaxLatticeDefault(model,input).bestOutputSequence();
    Sequence correctedSequence=(Sequence)predictions.get(i);
    Segment correctedSegment=(Segment)correctedSegments.get(i);
    if (correctedSegment == null || (errorsInUncorrected && !containsErrorInUncorrectedSegments(trueSequence,predSequence,correctedSequence,correctedSegment)))     continue;
    numCorrectedSequences++;
    totalTokens+=trueSequence.size();
    boolean[] predictedMatches=getMatches(trueSequence,predSequence);
    boolean[] correctedMatches=getMatches(trueSequence,correctedSequence);
    for (int j=0; j < predictedMatches.length; j++) {
      numPredictedCorrect+=predictedMatches[j] ? 1 : 0;
      numCorrectedCorrect+=correctedMatches[j] ? 1 : 0;
      if (predictedMatches[j] && !correctedMatches[j])       numCorrect2Incorrect++;
 else       if (!predictedMatches[j] && correctedMatches[j])       numIncorrect2Correct++;
      if (j < correctedSegment.getStart() || j > correctedSegment.getEnd()) {
        totalTokensInUncorrectedRegion++;
        if (!predictedMatches[j] && correctedMatches[j])         numPropagatedIncorrect2Correct++;
        numUncorrectedCorrectBeforePropagation+=predictedMatches[j] ? 1 : 0;
        numUncorrectedCorrectAfterPropagation+=correctedMatches[j] ? 1 : 0;
      }
    }
  }
  double tokenAccuracyBeforeCorrection=(double)numPredictedCorrect / totalTokens;
  double tokenAccuracyAfterCorrection=(double)numCorrectedCorrect / totalTokens;
  double uncorrectedRegionAccuracyBeforeCorrection=(double)numUncorrectedCorrectBeforePropagation / totalTokensInUncorrectedRegion;
  double uncorrectedRegionAccuracyAfterCorrection=(double)numUncorrectedCorrectAfterPropagation / totalTokensInUncorrectedRegion;
  outputStream.println(description + "\nEvaluating effect of error-propagation in sequences containing at least one token error:" + "\ntotal number correctedsequences: " + numCorrectedSequences + "\ntotal number tokens: " + totalTokens + "\ntotal number tokens in \"uncorrected region\":" + totalTokensInUncorrectedRegion + "\ntotal number correct tokens before correction:" + numPredictedCorrect + "\ntotal number correct tokens after correction:" + numCorrectedCorrect + "\ntoken accuracy before correction: " + tokenAccuracyBeforeCorrection + "\ntoken accuracy after correction: " + tokenAccuracyAfterCorrection + "\nnumber tokens corrected by propagation: " + numPropagatedIncorrect2Correct + "\nnumber tokens made incorrect by propagation: " + numCorrect2Incorrect + "\ntoken accuracy of \"uncorrected region\" before propagation: " + uncorrectedRegionAccuracyBeforeCorrection + "\ntoken accuracy of \"uncorrected region\" after propagataion: " + uncorrectedRegionAccuracyAfterCorrection);
}
