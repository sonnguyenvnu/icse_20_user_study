public Instance pipe(Instance carrier){
  TokenSequence ts=(TokenSequence)carrier.getData();
  FeatureSequence ret=new FeatureSequence((Alphabet)getDataAlphabet(),ts.size());
  for (int i=0; i < ts.size(); i++) {
    ret.add(ts.get(i).getText());
  }
  carrier.setData(ret);
  return carrier;
}
