@Override public void print(){
  final Alphabet dict=getAlphabet();
  final LabelAlphabet labelDict=getLabelAlphabet();
  int numFeatures=dict.size() + 1;
  int numLabels=labelDict.size();
  for (int li=0; li < numLabels; li++) {
    System.out.println("FEATURES FOR CLASS " + labelDict.lookupObject(li));
    System.out.println(" <default> " + parameters[li * numFeatures + defaultFeatureIndex]);
    for (int i=0; i < defaultFeatureIndex; i++) {
      Object name=dict.lookupObject(i);
      double weight=parameters[li * numFeatures + i];
      System.out.println(" " + name + " " + weight);
    }
  }
}
