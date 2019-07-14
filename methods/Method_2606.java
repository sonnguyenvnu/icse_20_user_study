private SimpleMDAGNode transitionBruteForce(SimpleMDAGNode[] mdagDataArray,char letter){
  int onePastTransitionSetEndIndex=transitionSetBeginIndex + transitionSetSize;
  SimpleMDAGNode targetNode=null;
  for (int i=transitionSetBeginIndex; i < onePastTransitionSetEndIndex; i++) {
    if (mdagDataArray[i].getLetter() == letter) {
      targetNode=mdagDataArray[i];
      break;
    }
  }
  return targetNode;
}
