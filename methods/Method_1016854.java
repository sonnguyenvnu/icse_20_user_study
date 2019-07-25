public Instance pipe(Instance carrier){
  TokenSequence ts=(TokenSequence)carrier.getData();
  for (int i=0; i < ts.size(); i++) {
    Token t=ts.get(i);
    String s=t.getText();
    int slen=s.length();
    if (slen > suffixLength)     t.setFeatureValue((prefix + s.substring(slen - suffixLength,slen)),1.0);
  }
  return carrier;
}
