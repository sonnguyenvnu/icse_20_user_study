public Instance pipe(Instance carrier){
  TokenSequence ts=(TokenSequence)carrier.getData();
  for (int i=0; i < ts.size(); i++) {
    Token t=ts.get(i);
    String s=t.getText();
    String conS=s;
    if (conS.startsWith("("))     conS=conS.substring(1);
    if (conS.endsWith(")") || conS.endsWith("."))     conS=conS.substring(0,conS.length() - 1);
    if (lexicon.contains(ignoreCase ? s.toLowerCase() : s))     t.setFeatureValue(name,1.0);
    if (conS.compareTo(s) != 0) {
      if (lexicon.contains(ignoreCase ? conS.toLowerCase() : conS))       t.setFeatureValue(name,1.0);
    }
  }
  return carrier;
}
