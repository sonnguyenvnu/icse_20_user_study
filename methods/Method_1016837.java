public Instance pipe(Instance carrier){
  Object target=carrier.getTarget();
  if (target == null)   ;
 else   if (target instanceof LabelSequence)   ;
 else   if (target instanceof FeatureSequence) {
    LabelAlphabet dict=(LabelAlphabet)getTargetAlphabet();
    FeatureSequence fs=(FeatureSequence)target;
    Label[] lbls=new Label[fs.size()];
    for (int i=0; i < fs.size(); i++) {
      lbls[i]=dict.lookupLabel(fs.getObjectAtPosition(i));
    }
    carrier.setTarget(new LabelSequence(lbls));
  }
 else   if (target instanceof TokenSequence) {
    Alphabet v=getTargetAlphabet();
    TokenSequence ts=(TokenSequence)target;
    int indices[]=new int[ts.size()];
    for (int i=0; i < ts.size(); i++)     indices[i]=v.lookupIndex(ts.get(i).getText());
    LabelSequence ls=new LabelSequence((LabelAlphabet)getTargetAlphabet(),indices);
    carrier.setTarget(ls);
  }
 else   if (target instanceof LabelsSequence) {
    LabelAlphabet dict=(LabelAlphabet)getTargetAlphabet();
    LabelsSequence lblseq=(LabelsSequence)target;
    Label[] labelArray=new Label[lblseq.size()];
    for (int i=0; i < lblseq.size(); i++) {
      Labels lbls=lblseq.getLabels(i);
      if (lbls.size() != 1)       throw new IllegalArgumentException("Cannot convert Labels at position " + i + " : " + lbls);
      labelArray[i]=dict.lookupLabel(lbls.get(0).getEntry());
    }
    LabelSequence ls=new LabelSequence(labelArray);
    carrier.setTarget(ls);
  }
 else {
    throw new IllegalArgumentException("Unrecognized target type: " + target);
  }
  return carrier;
}
