public Instance pipe(Instance carrier){
  TokenSequence ts=(TokenSequence)carrier.getData();
  TokenSequence ret=new TokenSequence();
  Token prevToken=null;
  for (int i=0; i < ts.size(); i++) {
    Token t=ts.get(i);
    if (!stoplist.contains(caseSensitive ? t.getText() : t.getText().toLowerCase())) {
      ret.add(t);
      prevToken=t;
    }
 else     if (markDeletions && prevToken != null)     prevToken.setProperty(FeatureSequenceWithBigrams.deletionMark,t.getText());
  }
  carrier.setData(ret);
  return carrier;
}
