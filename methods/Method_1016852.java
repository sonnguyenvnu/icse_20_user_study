public Instance pipe(Instance carrier){
  TokenSequence ts=(TokenSequence)carrier.getData();
  for (int i=0; i < ts.size(); i++) {
    Token t=ts.get(i);
    String s=t.getText();
    String conS=s;
    if (conS.startsWith("("))     conS=conS.substring(1);
    if (conS.endsWith(")") || conS.endsWith("."))     conS=conS.substring(0,conS.length() - 1);
    if (regex.matcher(s).matches())     t.setFeatureValue(feature,1.0);
    if (conS.compareTo(s) != 0) {
      if (regex.matcher(conS).matches())       t.setFeatureValue(feature,1.0);
    }
  }
  return carrier;
}
